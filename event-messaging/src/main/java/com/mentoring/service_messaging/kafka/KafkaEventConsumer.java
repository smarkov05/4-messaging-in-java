package com.mentoring.service_messaging.kafka;

import com.mentoring.service_dto.model.Event;
import com.mentoring.service_messaging.kafka.config.KafkaEventConsumerConfig;
import com.mentoring.service_messaging.kafka.config.KafkaEventTopicConfig;
import com.mentoring.service_messaging.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@Configuration
@Profile("kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final JsonConvertor jsonConvertor;

    @KafkaListener(
            groupId = KafkaEventConsumerConfig.EVENT_REQUEST_CONSUMER_GROUP_ID,
            topics = KafkaEventTopicConfig.CREATE_EVENT_REQUEST_TOPIC)
    public void onCreateRequest(@Payload List<String> eventsJson) {
        List<Event> events = eventsJson.stream()
                .map(jsonConvertor::convertToEvent)
                .toList();
        log.info("Consume events on creating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @KafkaListener(
            groupId = KafkaEventConsumerConfig.EVENT_REQUEST_CONSUMER_GROUP_ID,
            topics = KafkaEventTopicConfig.UPDATE_EVENT_REQUEST_TOPIC)
    public void onUpdateRequest(List<String> eventsJson) {
        List<Event> events = eventsJson.stream()
                .map(jsonConvertor::convertToEvent)
                .toList();
        log.info("Consume events on updating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @KafkaListener(
            groupId = KafkaEventConsumerConfig.EVENT_REQUEST_CONSUMER_GROUP_ID,
            topics = KafkaEventTopicConfig.DELETE_EVENT_REQUEST_TOPIC)
    public void onDeleteRequest(List<String> eventIds) {
        List<Long> toDeleteIds = eventIds.stream()
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
        log.info("Consume events on deleting events request. Quantity events {}", eventIds.size());
        toDeleteIds.forEach(id -> System.out.printf("Event id to delete: %d%n", id));
    }
}
