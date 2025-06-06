package dmk.poc.allyoucanread.service;

import dmk.poc.allyoucanread.dto.BookDto;
import dmk.poc.allyoucanread.dto.BookEventDto;
import dmk.poc.allyoucanread.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "timestamp", source = "timestamp")
    BookDto mapToDto(Book book);

    Book mapToModel(BookDto bookDto);

    @Mapping(target = "timestamp", expression = "java( mapTimestamp(java.time.Instant.now()) )")
    BookDto mapToDto(BookEventDto bookEventDto);

    default String mapTimestamp(Instant timestamp) {
        System.out.println("Mapping timestamp: " + timestamp);
        return timestamp != null ? timestamp.toString() : null;
    }
}
