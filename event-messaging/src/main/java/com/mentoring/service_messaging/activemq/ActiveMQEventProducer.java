package com.mentoring.service_messaging.activemq;

import com.mentoring.service_api.EventMessaging;
import com.mentoring.service_dto.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("activemq")
@Transactional
@RequiredArgsConstructor
public class ActiveMQEventProducer implements EventMessaging {
    private static final String CREATE_EVENT_NOTIFICATION = "create.event.notification";
    private static final String UPDATE_EVENT_NOTIFICATION = "update.event.notification";
    private static final String DELETE_EVENT_NOTIFICATION = "delete.event.notification";

    private final JmsTemplate jmsTemplate;

    @Override
    public void createEvent(Event event) {
        sendMessage(CREATE_EVENT_NOTIFICATION, event);
    }

    @Override
    public void updateEvent(Event event) {
        sendMessage(UPDATE_EVENT_NOTIFICATION, event);
    }

    @Override
    public void delete(Long id) {
        jmsTemplate.convertAndSend(DELETE_EVENT_NOTIFICATION, id, postProcessor -> {
            postProcessor.setStringProperty("eventId", id.toString());
            return postProcessor;
        });
    }

    private void sendMessage(String queue, Event event) {
        jmsTemplate.convertAndSend(queue, event, postProcessor -> {
            postProcessor.setStringProperty("eventId", event.getId().toString());
            postProcessor.setStringProperty("dateTime", event.getDateTime().toString());
            postProcessor.setStringProperty("type", event.getType().name());
            return postProcessor;
        });
    }
}
