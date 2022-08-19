package com.mentoring.service_messaging.rebbitmq;

import com.mentoring.service_api.EventMessaging;
import com.mentoring.service_dto.model.Event;
import com.mentoring.service_messaging.rebbitmq.config.RabbitMQEventProducerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
@RequiredArgsConstructor
public class RabbitMQEventProducer implements EventMessaging {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void createEvent(Event event) {
        rabbitTemplate.convertAndSend(
                RabbitMQEventProducerConfig.EVENT_NOTIFICATION_EXCHANGE,
                RabbitMQEventProducerConfig.CREATE_EVENT_ROUTING_KEY,
                event);
    }

    @Override
    public void updateEvent(Event event) {
        rabbitTemplate.convertAndSend(
                RabbitMQEventProducerConfig.EVENT_NOTIFICATION_EXCHANGE,
                RabbitMQEventProducerConfig.UPDATE_EVENT_ROUTING_KEY,
                event);
    }

    @Override
    public void delete(Long id) {
        rabbitTemplate.convertAndSend(
                RabbitMQEventProducerConfig.EVENT_NOTIFICATION_EXCHANGE,
                RabbitMQEventProducerConfig.DELETE_EVENT_ROUTING_KEY,
                id);
    }
}
