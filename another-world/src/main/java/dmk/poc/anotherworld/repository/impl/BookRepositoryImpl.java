package dmk.poc.anotherworld.repository.impl;

import dmk.poc.anotherworld.model.Book;
import dmk.poc.anotherworld.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepositoryImpl implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public Book save(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return List.copyOf(books);
    }
}
