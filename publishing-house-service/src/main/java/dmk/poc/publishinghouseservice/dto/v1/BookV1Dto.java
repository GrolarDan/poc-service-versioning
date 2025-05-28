package dmk.poc.publishinghouseservice.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BookDto")
public record BookV1Dto(String isbn, String title, String author, String publisher, String genre) {
}
