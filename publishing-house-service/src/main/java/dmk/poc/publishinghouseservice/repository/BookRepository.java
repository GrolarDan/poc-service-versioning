package dmk.poc.publishinghouseservice.repository;

import dmk.poc.publishinghouseservice.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    Book findByIsbn(String isbn);
    Book deleteByIsbn(String isbn);
    List<Book> findAll();
}
