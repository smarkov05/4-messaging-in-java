package com.mentoring.service_messaging.activemq;

import com.mentoring.service_dto.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Profile("activemq")
public class ActiveMQEventConsumer {
    private static final String CREATE_EVENT_REQUEST = "create.event.request";
    private static final String UPDATE_EVENT_REQUEST = "update.event.request";
    private static final String DELETE_EVENT_REQUEST = "delete.event.request";

    @JmsListener(destination = CREATE_EVENT_REQUEST)
    public void onCreateRequest(@Payload List<Event> events) {
        log.info("Consume events on creating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @JmsListener(destination = UPDATE_EVENT_REQUEST)
    public void onUpdateRequest(@Payload List<Event> events) {
        log.info("Consume events on updating events request. Quantity events {}", events.size());
        events.forEach(System.out::println);
    }

    @JmsListener(destination = DELETE_EVENT_REQUEST)
    public void onDeleteRequest(@Payload List<Long> eventIds) {
        log.info("Consume events on deleting events request. Quantity events {}", eventIds.size());
        eventIds.forEach(id -> System.out.printf("Event id to delete: %d%n", id));
    }
}
