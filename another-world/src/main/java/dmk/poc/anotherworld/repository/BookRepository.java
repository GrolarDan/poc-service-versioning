package dmk.poc.anotherworld.repository;


import dmk.poc.anotherworld.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}
