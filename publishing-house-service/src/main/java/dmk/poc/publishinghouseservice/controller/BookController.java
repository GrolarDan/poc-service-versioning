package dmk.poc.publishinghouseservice.controller;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = BookController.VERSION_PATH + BookController.BASE_PATH)
@RequiredArgsConstructor
public class BookController {
    static final String VERSION_PATH = "/v1";
    static final String BASE_PATH = "/books";

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        // Logic to create a book
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{isbn}")
    public BookDto updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        // Logic to update a book
        return bookService.updateBook(isbn, bookDto);
    }

    @GetMapping("/{isbn}")
    public BookDto getBook(@PathVariable String isbn) {
        // Logic to get a book
        return bookService.getBook(isbn);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        // Logic to delete a book
        bookService.deleteBook(isbn);
    }

    @PostMapping("/store-book")
    public BookDto storeBook() {
        // Logic to store a book
        return bookService.storeBook();
    }
}
