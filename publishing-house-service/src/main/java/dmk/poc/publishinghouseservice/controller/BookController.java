package dmk.poc.publishinghouseservice.controller;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = BookController.VERSION_PATH + BookController.BASE_PATH)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book API")
public class BookController {
    static final String VERSION_PATH = "/v1";
    static final String BASE_PATH = "/books";

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Create a new book")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        // Logic to create a book
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{isbn}")
    @Operation(summary = "Update an existing book")
    public BookDto updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        // Logic to update a book
        return bookService.updateBook(isbn, bookDto);
    }

    @GetMapping("/{isbn}")
    @Operation(summary = "Get a book by ISBN")
    public BookDto getBook(@PathVariable String isbn) {
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
    public BookDto storeBook() {
        // Logic to store a book
        return bookService.storeBook();
    }
}
