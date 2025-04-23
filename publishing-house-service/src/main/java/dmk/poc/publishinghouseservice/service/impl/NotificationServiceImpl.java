package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.service.NotificationService;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final String EVENT_VERSION_V1 = "v1";

    private final SnsTemplate snsTemplate;
    @Value("${app.topic-name}")
    private final String topicName;

    @Override
    public void sendNotification(BookDto bookDto, BookEventType eventType) {
        Map<String, Object> headers = Map.of(
                "entityId", bookDto.isbn(),
                "eventVersion", EVENT_VERSION_V1,
                "eventType", eventType.getValue(),
                MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
        );

        snsTemplate.sendNotification(topicName, SnsNotification.builder(bookDto).headers(headers).build());
    }
}
