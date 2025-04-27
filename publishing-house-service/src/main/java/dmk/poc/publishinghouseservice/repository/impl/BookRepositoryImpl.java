package dmk.poc.publishinghouseservice.repository.impl;

import dmk.poc.publishinghouseservice.model.Book;
import dmk.poc.publishinghouseservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Book save(Book book) {
        if (book == null || book.isbn() == null) {
            throw new IllegalArgumentException("Book and ISBN cannot be null");
        }
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }

    @Override
    public Book deleteByIsbn(String isbn) {
        return books.remove(isbn);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
