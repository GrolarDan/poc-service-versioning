package dmk.poc.publishinghouseservice.repository.impl;

import dmk.poc.publishinghouseservice.dto.GenreType;
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

    public BookRepositoryImpl() {
        // Sample data with genre only for v1 simulation
        books.put("978-3-16-148410-0", new Book("978-3-16-148410-0", "Book Title 1", "Author 1", "Publisher 1", GenreType.SCIENCE_FICTION.getValue(), null));
        books.put("978-1-234-56789-7", new Book("978-1-234-56789-7", "Book Title 2", "Author 2", "Publisher 2", GenreType.FANTASY.getValue(), null));
        books.put("978-0-123-45678-9", new Book("978-0-123-45678-9", "Book Title 3", "Author 3", "Publisher 3", GenreType.HORROR.getValue(), null));
        books.put("978-0-98765432-1", new Book("978-0-98765432-1", "Book Title 4", "Author 4", "Publisher 4", GenreType.MYSTERY.getValue(), null));
    }

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
