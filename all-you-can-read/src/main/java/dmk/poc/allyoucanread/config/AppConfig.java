package dmk.poc.allyoucanread.config;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;
import java.util.List;

@Configuration
public class AppConfig {

//    @Bean
//    SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
//        return SqsMessageListenerContainerFactory
//                .builder()
//                .configure(options -> options
//                        .messageAttributeNames(List.of("entityId", "eventVersion", "eventType"))
//                        .pollTimeout(Duration.ofSeconds(10)))
//                .sqsAsyncClient(sqsAsyncClient)
//                .build();
//    }

}
