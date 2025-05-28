# poc-service-versioning
POC for versioning of REST service

This repository contains a proof of concept for versioning a REST service using Spring Boot and Spring Cloud.   
The goal is to demonstrate how to manage different versions of a service events and how to handle messages in consumers.

We have three services:
* Publishing House Service - the book publisher service which produces events
* All You Can Read Service - the book store service which consumes events (all genres)
* Another World Service - the book store service which consumes events (only fiction)

## Publish House Service (PHS)
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

## All You Can Read Service (AYCRS)
The All You Can Read Service is a Spring Boot application that consumes events from the queue (SQS).  
The queue (SQS) is subscribed to the topic (SNS) using a subscription.  
The service publishes a UI page where you can see the list of books.  
The page is available at the following URL:  
`http://localhost:3100`

## Another World Service (AWS)
The Another World Service is a Spring Boot application that consumes events from the queue (SQS).  
The queue (SQS) is subscribed to the topic (SNS) using a subscription.  
The service consumes only events with the genre from "Fiction" type. This happens in the `BookEventHandler` class.  
The service publishes a UI page where you can see the list of books.  
The page is available at the following URL:  
`http://localhost:3101`

# Process of version update
1. Create a new version of the PHS service (e.g. V2) with the new structure of the event.
   1. Update model class `Book` enhancing with the `genreId` field.
   2. Create a new class `BookV2Dto` with the new structure of the event.
   3. Update the `NotificationService` class to publish both event versions.
   4. Deploy the new version of the service (PHS) with the new structure of the event.
2. Create a new version of the event handler in the AYCRS and AWS services.
   1. Create a new class `BookEventV2Dto` with the new structure of the event.
   2. Create a new class `BookEventV2Handler` to handle the new version of the event.
   3. Update the UI page in the AYCRS and AWS services to display the new version of the event.
   4. Deploy both services (AYCRS, AWS) with the new and old version of the event handler.
3. Update the AYCRS and AWS queues filters to handle the new version of the event.
   1. It starts handling only the new version of the event.
   2. The remaining old version events are handled by the old version of the event handler.
4. Delete the old version of the event handler in the AYCRS and AWS services.
   1. Delete the old version of the event handler.
   2. Deploy both services (AYCRS, AWS) with the new version of the event handler only.
5. When there is no topic subscription with filter on `V1` version, we can delete the old version of the event in the PHS service.
   1. Delete the old version of the event in the PHS service.
   2. Deploy the new version of the service (PHS) with the new structure of the event only.
6. Job is **DONE** 