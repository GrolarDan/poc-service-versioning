package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.service.BookService;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Map<String, BookDto> books = new HashMap<>();

    private final Faker faker;

    @Override
    public BookDto storeBook() {
        var fakerBook = faker.book();
        return createBook(
                new BookDto(faker.code().ean13(), fakerBook.title(), fakerBook.author(), fakerBook.publisher(), fakerBook.genre())
        );
    }

    @Override
    public List<BookDto> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookDto == null || bookDto.isbn() == null) {
            throw new IllegalArgumentException("BookDto and ISBN cannot be null");
        }

        if (books.containsKey(bookDto.isbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }

        return books.put(bookDto.isbn(), bookDto);
    }

    @Override
    public BookDto updateBook(String isbn, BookDto bookDto) {
        if (isbn == null || bookDto == null) {
            throw new IllegalArgumentException("ISBN and BookDto cannot be null");
        }

        if (!isbn.equals(bookDto.isbn())) {
            throw new IllegalArgumentException("ISBN and BookDto does not match");
        }

        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book with this ISBN does not exist");
        }

        return books.put(isbn, bookDto);
    }

    @Override
    public BookDto getBook(String isbn) {
        return books.get(isbn);
    }

    @Override
    public void deleteBook(String isbn) {
        books.remove(isbn);
    }
}
