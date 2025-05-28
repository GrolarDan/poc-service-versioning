package dmk.poc.publishinghouseservice.service.v1;

import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;

import java.util.List;

public interface BookV1Service {
    BookV1Dto storeBook();

    List<BookV1Dto> getAllBooks();

    BookV1Dto createBook(BookV1Dto bookDto);

    BookV1Dto updateBook(String isbn, BookV1Dto bookDto);

    BookV1Dto getBook(String isbn);

    void deleteBook(String isbn);
}
