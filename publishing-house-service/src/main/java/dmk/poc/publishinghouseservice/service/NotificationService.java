package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;

public interface NotificationService {
    void sendNotification(BookDto bookDto, BookEventType eventType);
}
