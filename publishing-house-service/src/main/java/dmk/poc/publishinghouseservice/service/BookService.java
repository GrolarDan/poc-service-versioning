package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEditDto;

import java.util.List;

public interface BookService {
    BookDto storeBook();

    List<BookDto> getAllBooks();

    BookDto createBook(BookEditDto bookDto);

    BookDto updateBook(String isbn, BookEditDto bookDto);

    BookDto getBook(String isbn);

    void deleteBook(String isbn);
}
