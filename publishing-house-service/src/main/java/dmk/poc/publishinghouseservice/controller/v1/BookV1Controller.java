package dmk.poc.publishinghouseservice.controller.v1;

import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;
import dmk.poc.publishinghouseservice.service.v1.BookV1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = BookV1Controller.VERSION_PATH + BookV1Controller.BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book API")
public class BookV1Controller {
    static final String VERSION_PATH = "v1";
    static final String BASE_PATH = "/books";

    private final BookV1Service bookService;

    @GetMapping
    @Operation(summary = "Get all books")
    public List<BookV1Dto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Create a new book")
    public BookV1Dto createBook(@RequestBody BookV1Dto bookDto) {
        // Logic to create a book
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{isbn}")
    @Operation(summary = "Update an existing book")
    public BookV1Dto updateBook(@PathVariable String isbn, @RequestBody BookV1Dto bookDto) {
        // Logic to update a book
        return bookService.updateBook(isbn, bookDto);
    }

    @GetMapping("/{isbn}")
    @Operation(summary = "Get a book by ISBN")
    public BookV1Dto getBook(@PathVariable String isbn) {
        // Logic to get a book
        return bookService.getBook(isbn);
    }

    @DeleteMapping("/{isbn}")
    @Operation(summary = "Delete a book by ISBN")
    public void deleteBook(@PathVariable String isbn) {
        // Logic to delete a book
        bookService.deleteBook(isbn);
    }

    @PostMapping("/store-book")
    @Operation(summary = "Store a book with random data")
    public BookV1Dto storeBook() {
        // Logic to store a book
        return bookService.storeBook();
    }
}
