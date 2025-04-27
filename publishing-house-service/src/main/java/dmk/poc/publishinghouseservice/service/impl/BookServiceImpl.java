package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.repository.BookRepository;
import dmk.poc.publishinghouseservice.service.BookMapper;
import dmk.poc.publishinghouseservice.service.BookService;
import dmk.poc.publishinghouseservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final Faker faker;
    private final NotificationService notificationService;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public BookDto storeBook() {
        var fakerBook = faker.book();
        return createBook(
                new BookDto(faker.code().ean13(), fakerBook.title(), fakerBook.author(), fakerBook.publisher(), fakerBook.genre())
        );
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::mapToDto).toList();
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        log.info("Creating book: {}", bookDto);

        if (bookDto == null || bookDto.isbn() == null) {
            throw new IllegalArgumentException("BookDto and ISBN cannot be null");
        }

        if (bookRepository.findByIsbn(bookDto.isbn()) != null) {
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }

        bookRepository.save(bookMapper.mapToEntity(bookDto));
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

        if (bookRepository.findByIsbn(isbn) == null) {
            throw new IllegalArgumentException("Book with this ISBN does not exist");
        }

        bookRepository.save(bookMapper.mapToEntity(bookDto));
        notificationService.sendNotification(bookDto, BookEventType.BOOK_UPDATED);

        return bookDto;
    }

    @Override
    public BookDto getBook(String isbn) {
        return bookMapper.mapToDto(bookRepository.findByIsbn(isbn));
    }

    @Override
    public void deleteBook(String isbn) {
        log.info("Deleting book with ISBN: {}", isbn);

        var result = bookRepository.deleteByIsbn(isbn);
        if (result != null) {
            notificationService.sendNotification(bookMapper.mapToDto(result), BookEventType.BOOK_DELETED);
        }
    }
}
