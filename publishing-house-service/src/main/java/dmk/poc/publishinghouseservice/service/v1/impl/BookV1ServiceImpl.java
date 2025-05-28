package dmk.poc.publishinghouseservice.service.v1.impl;

import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.repository.BookRepository;
import dmk.poc.publishinghouseservice.service.v1.BookV1Mapper;
import dmk.poc.publishinghouseservice.service.v1.BookV1Service;
import dmk.poc.publishinghouseservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookV1ServiceImpl implements BookV1Service {

    private final Faker faker;
    private final NotificationService notificationService;
    private final BookV1Mapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public BookV1Dto storeBook() {
        var fakerBook = faker.book();
        return createBook(
                new BookV1Dto(faker.code().ean13(), fakerBook.title(), fakerBook.author(), fakerBook.publisher(), fakerBook.genre())
        );
    }

    @Override
    public List<BookV1Dto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::mapToDto).toList();
    }

    @Override
    public BookV1Dto createBook(BookV1Dto bookDto) {
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
    public BookV1Dto updateBook(String isbn, BookV1Dto bookDto) {
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
    public BookV1Dto getBook(String isbn) {
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
