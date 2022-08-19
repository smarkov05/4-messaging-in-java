package com.mentoring.service_messaging.rebbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("rabbitmq")
public class RabbitMQEventProducerConfig {
    private static final String CREATE_EVENT_NOTIFICATION_QUEUE = "event-notification.on-create.queue";
    private static final String UPDATE_EVENT_NOTIFICATION_QUEUE = "event-notification.on-update.queue";
    private static final String DELETE_EVENT_NOTIFICATION_QUEUE = "event-notification.on-delete.queue";
    private static final String EVENT_NOTIFICATION_DEAD_QUEUE = "event-notification.dead-letter.queue";

    public static final String EVENT_NOTIFICATION_EXCHANGE = "event-notification.exchange";
    public static final String CREATE_EVENT_ROUTING_KEY = "event-notification.create.rk";
    public static final String UPDATE_EVENT_ROUTING_KEY = "event-notification.update.rk";
    public static final String DELETE_EVENT_ROUTING_KEY = "event-notification.delete.rk";

    @Bean
    public Queue createEventNotificationQueue() {
        return new Queue(CREATE_EVENT_NOTIFICATION_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue updateEventNotificationQueue() {
        return new Queue(UPDATE_EVENT_NOTIFICATION_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue deleteEventNotificationQueue() {
        return new Queue(DELETE_EVENT_NOTIFICATION_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue deadLetterEventNotificationQueue() {
        return new Queue(EVENT_NOTIFICATION_DEAD_QUEUE, true, false, false);
    }

    @Bean
    public Exchange eventNotificationExchange() {
        return ExchangeBuilder
                .topicExchange(EVENT_NOTIFICATION_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding createEventNotificationBinding() {
        return BindingBuilder
                .bind(createEventNotificationQueue())
                .to(eventNotificationExchange())
                .with(CREATE_EVENT_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding updateEventNotificationBinding() {
        return BindingBuilder
                .bind(updateEventNotificationQueue())
                .to(eventNotificationExchange())
                .with(UPDATE_EVENT_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding deleteEventNotificationBinding() {
        return BindingBuilder
                .bind(deleteEventNotificationQueue())
                .to(eventNotificationExchange())
                .with(DELETE_EVENT_ROUTING_KEY)
                .noargs();
    }

    private Map<String, Object> deadLetterQueueArgs() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", EVENT_NOTIFICATION_DEAD_QUEUE);
        return args;
    }
}
