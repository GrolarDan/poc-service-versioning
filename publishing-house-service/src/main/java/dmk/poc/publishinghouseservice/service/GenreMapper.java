package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.GenreDto;
import dmk.poc.publishinghouseservice.dto.GenreType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "name", expression = "java(genreType.getValue())")
    @Mapping(target = "id", expression = "java(genreType.getId())")
    GenreDto mapFromGenreTypeToDto(GenreType genreType);

    @Mapping(target = "name", source = "genreId", qualifiedByName = "mapGenreIdToName")
    @Mapping(target = "id", source = "genreId")
    GenreDto mapFromGenreIdToDto(Integer genreId);

    @Mapping(target = "name", source = "genreName")
    @Mapping(target = "id", expression = "java(mapGenreNameToType(genreName).getId())")
    GenreDto mapFromGenreNameToDto(String genreName);

    @Named("mapGenreIdToName")
    default String mapGenreIdToName(Integer genreId) {
        return GenreType.fromId(genreId).orElseThrow().getValue();
    }

    @Named("mapGenreNameToType")
    default GenreType mapGenreNameToType(String genreName) {
        return GenreType.fromValue(genreName).orElseThrow();
    }
}
