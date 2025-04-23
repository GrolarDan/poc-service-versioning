package dmk.poc.allyoucanread.repository;

import dmk.poc.allyoucanread.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}
