package dmk.poc.allyoucanread.dto;

public record BookDto(String isbn, String title, String author, String publisher, String genre, String timestamp) {
    // No additional methods or fields are needed for this record
}
