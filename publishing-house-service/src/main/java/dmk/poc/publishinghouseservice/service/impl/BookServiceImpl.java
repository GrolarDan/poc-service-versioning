package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.service.BookService;
import dmk.poc.publishinghouseservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final Map<String, BookDto> books = new HashMap<>();

    private final Faker faker;
    private final NotificationService notificationService;

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
        log.info("Creating book: {}", bookDto);

        if (bookDto == null || bookDto.isbn() == null) {
            throw new IllegalArgumentException("BookDto and ISBN cannot be null");
        }

        if (books.containsKey(bookDto.isbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }

        books.put(bookDto.isbn(), bookDto);
        notificationService.sendNotification(bookDto, BookEventType.BOOK_CREATED);

        return bookDto;
    }

    @Override
    public BookDto updateBook(String isbn, BookDto bookDto) {
        log.info("Updating book: {}", bookDto);

        if (isbn == null || bookDto == null) {
            throw new IllegalArgumentException("ISBN and BookDto cannot be null");
        }

        if (!isbn.equals(bookDto.isbn())) {
            throw new IllegalArgumentException("ISBN and BookDto does not match");
        }

        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book with this ISBN does not exist");
        }

        books.put(isbn, bookDto);
        notificationService.sendNotification(bookDto, BookEventType.BOOK_UPDATED);

        return bookDto;
    }

    @Override
    public BookDto getBook(String isbn) {
        return books.get(isbn);
    }

    @Override
    public void deleteBook(String isbn) {
        log.info("Deleting book with ISBN: {}", isbn);

        var result = books.remove(isbn);
        if (result != null) {
            notificationService.sendNotification(result, BookEventType.BOOK_DELETED);
        }
    }
}
