package dmk.poc.publishinghouseservice.service.v1;

import dmk.poc.publishinghouseservice.dto.GenreType;
import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;
import dmk.poc.publishinghouseservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookV1Mapper {

    @Mapping(target = "genreId", source = "genre", qualifiedByName = "mapGenreToId")
    Book mapToEntity(BookV1Dto dto);

    // We assume that genre always exists because it was saved in v1 version and also is saved in v2 version
    BookV1Dto mapToDto(Book entity);

    @Named("mapGenreToId")
    default Integer mapGenreToId(String genre) {
        return genre != null ? GenreType.fromValue(genre).orElseThrow().getId() : null;
    }
}
