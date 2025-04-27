# poc-service-versioning
POC for versioning of REST service

This repository contains a proof of concept for versioning a REST service using Spring Boot and Spring Cloud.   
The goal is to demonstrate how to manage different versions of a service events and how to handle messages in consumers.

We have three services:
* Publishing House Service - the book publisher service which produces events
* All You Can Read Service - the book store service which consumes events (all genres)
* Another World Service - the book store service which consumes events (only fiction)

## Publish House Service
The Publishing House Service is a Spring Boot application that produces events.  
It generates a random book and sends it to the topic (SNS) using the `NotificationService` class.

### V1 version
The first version of the service produces events with the following structure:
```json
{
  "isbn": "<string>",
  "title": "<string>",
  "author": "<string>",
  "publisher": "<string>",
  "genre": "<string>"
}
```

### V2 version
The second version changes the book genre type from a string to an enum.
```json
{
  "isbn": "<string>",
  "title": "<string>",
  "author": "<string>",
  "publisher": "<string>",
  "genre": "<number>"
}
```

### Generate Book
The service publishes a swagger UI page where you can generate a book.
The page is available at the following URL:  
`http://localhost:3000/swagger-ui`

## All You Can Read Service
The All You Can Read Service is a Spring Boot application that consumes events from the queue (SQS).  
The queue (SQS) is subscribed to the topic (SNS) using a subscription.  
The service publishes a UI page where you can see the list of books.  
The page is available at the following URL:  
`http://localhost:3100`

## Another World Service
The Another World Service is a Spring Boot application that consumes events from the queue (SQS).  
The queue (SQS) is subscribed to the topic (SNS) using a subscription.  
The service consumes only events with the genre from "Fiction" type. This happens in the `BookEventHandler` class.  
The service publishes a UI page where you can see the list of books.  
The page is available at the following URL:  
`http://localhost:3101`
