package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.service.NotificationService;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final String EVENT_VERSION_V1 = "v1";

    private final SnsTemplate snsTemplate;
    @Value("${app.topic-name}")
    private final String topicName;

    @Override
    public void sendNotification(BookDto bookDto, BookEventType eventType) {
        Message<BookDto> message = MessageBuilder.withPayload(bookDto)
                .setHeader("entityId", bookDto.isbn())
                .setHeader("eventVersion", EVENT_VERSION_V1)
                .setHeader("eventType", eventType.getValue())
                .build();

        snsTemplate.send(topicName, message);
    }
}
