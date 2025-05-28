package dmk.poc.publishinghouseservice.dto;

public record BookEditDto(String isbn, String title, String author, String publisher, Integer genreId) {
    // No additional methods or fields are needed for this record
}
