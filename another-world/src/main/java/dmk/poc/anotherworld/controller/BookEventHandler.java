package dmk.poc.anotherworld.controller;

import dmk.poc.anotherworld.dto.BookEventDto;
import dmk.poc.anotherworld.service.BookMapper;
import dmk.poc.anotherworld.service.BookService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookEventHandler {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @SqsListener(value = "${app.sqs.name}")
    public void handleBookCreatedEvent(BookEventDto bookEventDto, @Headers Map<String, Object> headers) {
        log.info("Received book created event: {}", bookEventDto);
        var book = bookService.save(bookMapper.mapToDto(bookEventDto));
        log.info("Saved book {} with timestamp {}", book.isbn(), book.timestamp());
        log.info("Headers:");
        headers.forEach((key, value) -> log.info("\t{} = {}", key, value));

    }
}
