package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.BookDto;
import dmk.poc.publishinghouseservice.dto.BookEventType;
import dmk.poc.publishinghouseservice.dto.v1.BookV1Dto;

public interface NotificationService {
    void sendNotification(BookV1Dto bookDto, BookEventType eventType);
    void sendNotification(BookDto bookDto, BookEventType eventType);
}
