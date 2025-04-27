package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapToEntity(BookDto dto);
    BookDto mapToDto(Book entity);
}
