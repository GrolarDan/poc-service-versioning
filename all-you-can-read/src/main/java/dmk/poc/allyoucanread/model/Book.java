package dmk.poc.allyoucanread.model;

import java.time.Instant;

public record Book(String isbn, String title, String author, String publisher, String genre, Instant timestamp) {
}
