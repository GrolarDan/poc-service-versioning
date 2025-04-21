package dmk.poc.allyoucanread.controller;

import dmk.poc.allyoucanread.dto.BookEventDto;
import io.awspring.cloud.sqs.annotation.SnsNotificationMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class BookEventHandler {

    @SqsListener(value = "${app.sqs.name}")
    public void handleBookCreatedEvent(@SnsNotificationMessage BookEventDto bookEventDto, @Headers Map<String, Object> headers) {
        log.info("Received book created event: {}", bookEventDto);
//        log.info("Original message: {}", originalMessage);
//        log.info("Message attributes: {}", originalMessage.messageAttributes());
//        originalMessage.messageAttributes().forEach((key, value) -> log.info("\t{} = {}", key, value));
        log.info("Headers:");
        headers.forEach((key, value) -> log.info("\t{} = {}", key, value));
    }
}
