package com.mentoring.service_messaging.rebbitmq;

import com.mentoring.service_dto.model.Event;
import com.mentoring.service_messaging.rebbitmq.config.RabbitMQEventConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@Profile("rabbitmq")
public class RabbitMQEventConsumer {

    @RabbitListener(queues = RabbitMQEventConsumerConfig.CREATE_EVENT_REQUEST_QUEUE)
    public void onCreateRequest(@Payload List<Event> events) {
        log.info("Consume events on creating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @RabbitListener(queues = RabbitMQEventConsumerConfig.UPDATE_EVENT_REQUEST_QUEUE)
    public void onUpdateRequest(@Payload List<Event> events) {
        log.info("Consume events on updating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @RabbitListener(queues = RabbitMQEventConsumerConfig.DELETE_EVENT_REQUEST_QUEUE)
    public void onDeleteRequest(@Payload List<Long> eventIds) {
        log.info("Consume events on deleting events request. Quantity events {}", eventIds.size());
        eventIds.forEach(id -> System.out.printf("Event id to delete: %d%n", id));
    }
}
