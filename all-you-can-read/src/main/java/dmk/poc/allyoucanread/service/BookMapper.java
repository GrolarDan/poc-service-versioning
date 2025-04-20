package dmk.poc.allyoucanread.service;

import dmk.poc.allyoucanread.dto.BookDto;
import dmk.poc.allyoucanread.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "timestamp", source = "timestamp")
    BookDto mapToDto(Book book);

    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    Book mapToModel(BookDto bookDto);

    default String mapTimestamp(Instant timestamp) {
        System.out.println("Mapping timestamp: " + timestamp);
        return LocalDateTime.from(timestamp).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
