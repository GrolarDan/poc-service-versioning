package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.GenreDto;
import dmk.poc.publishinghouseservice.dto.GenreType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "name", source = "value")
    GenreDto mapToDto(GenreType genreType);
}
