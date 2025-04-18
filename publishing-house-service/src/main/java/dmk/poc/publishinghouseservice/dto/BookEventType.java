package dmk.poc.publishinghouseservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BookEventType {
    BOOK_CREATED("book-created"),
    BOOK_UPDATED("book-updated"),
    BOOK_DELETED("book-deleted");

    private final String value;
}
