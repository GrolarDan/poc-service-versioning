package dmk.poc.publishinghouseservice.dto;

public record BookDto(String isbn, String title, String author, String publisher, GenreDto genre) {
}
