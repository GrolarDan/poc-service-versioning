package dmk.poc.anotherworld.model;

import java.time.Instant;

public record Book(String isbn, String title, String author, String publisher, Instant timestamp) {
}
