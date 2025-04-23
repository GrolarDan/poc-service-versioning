package dmk.poc.allyoucanread.controller;

import dmk.poc.allyoucanread.dto.BookEventDto;
import dmk.poc.allyoucanread.service.BookMapper;
import dmk.poc.allyoucanread.service.BookService;
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
//        log.info("Original message: {}", originalMessage);
//        log.info("Message attributes: {}", originalMessage.messageAttributes());
//        originalMessage.messageAttributes().forEach((key, value) -> log.info("\t{} = {}", key, value));
        log.info("Headers:");
        headers.forEach((key, value) -> log.info("\t{} = {}", key, value));

    }
}
