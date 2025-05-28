package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.service.BookMapper;
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
    private static final String EVENT_VERSION_V2 = "v2";

    private final SnsTemplate snsTemplate;
    @Value("${app.topic-name}")
    private final String topicName;
    private final BookMapper bookMapper;

    @Override
    public void sendNotification(BookV1Dto bookDto, BookEventType eventType) {
        sendNotificationInternal(bookDto.isbn(), EVENT_VERSION_V1, eventType, bookDto);
        sendNotificationInternal(bookDto.isbn(), EVENT_VERSION_V2, eventType, bookMapper.mapFromV1ToV2Dto(bookDto));
    }

    @Override
    public void sendNotification(BookDto bookDto, BookEventType eventType) {
        sendNotificationInternal(bookDto.isbn(), EVENT_VERSION_V2, eventType, bookDto);
        sendNotificationInternal(bookDto.isbn(), EVENT_VERSION_V1, eventType, bookMapper.mapFromV2ToV1Dto(bookDto));
    }

    private <T> void sendNotificationInternal(String entityId, String eventVersion, BookEventType eventType, T payload) {
        Map<String, Object> headers = Map.of(
                "entityId", entityId,
                "eventVersion", eventVersion,
                "eventType", eventType.getValue(),
                MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
        );

        snsTemplate.sendNotification(topicName, SnsNotification.builder(payload).headers(headers).build());
    }
}
