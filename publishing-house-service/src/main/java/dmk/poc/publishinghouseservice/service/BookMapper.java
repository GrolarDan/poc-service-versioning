package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEditDto;
import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;
import dmk.poc.publishinghouseservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface BookMapper {

    @Mapping(target = "genre", source = "genreId", qualifiedByName = "mapGenreIdToName")
    Book mapToEntity(BookEditDto dto);

    // We assume that genre always exists because it was saved in v1 version and also is saved in v2 version
    @Mapping(target = "genre", source = "genre")
    BookDto mapToDto(Book entity);

    BookDto mapFromV1ToV2Dto(BookV1Dto dto);

    @Mapping(target = "genre", source = "genre.name")
    BookV1Dto mapFromV2ToV1Dto(BookDto dto);
}
