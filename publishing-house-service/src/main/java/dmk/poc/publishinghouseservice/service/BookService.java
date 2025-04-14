package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto storeBook();

    List<BookDto> getAllBooks();

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(String isbn, BookDto bookDto);

    BookDto getBook(String isbn);

    void deleteBook(String isbn);
}
