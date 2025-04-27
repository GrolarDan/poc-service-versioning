package dmk.poc.anotherworld.controller;

import dmk.poc.anotherworld.dto.BookEventDto;
import dmk.poc.anotherworld.service.BookMapper;
import dmk.poc.anotherworld.service.BookService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookEventHandler {
    private final BookService bookService;
    private final BookMapper bookMapper;

    private final List<String> GENRES = List.of("Fanfiction", "Fantasy", "Fiction narrative", "Fiction in verse", "Metafiction", "Mythology", "Realistic fiction", "Science fiction");

    @SqsListener(value = "${app.sqs.name}")
    public void handleBookCreatedEvent(BookEventDto bookEventDto) {
        log.info("Received book created event: {}", bookEventDto);
        if (CollectionUtils.containsAny(GENRES, bookEventDto.genre())) {
            var book = bookService.save(bookMapper.mapToDto(bookEventDto));
            log.info("Saved book {} with timestamp {}", book.isbn(), book.timestamp());
        } else {
            log.info("Book {} is not in the specified genres '{}', skipping save.", bookEventDto.isbn(), bookEventDto.genre());
        }
    }
}
