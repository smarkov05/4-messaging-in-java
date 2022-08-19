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
public class RabbitMQEventConsumerConfig {
    private static final String EVENT_REQUEST_EXCHANGE = "event-request.exchange";
    private static final String CREATE_EVENT_ROUTING_KEY = "event-request.create.rk";
    private static final String UPDATE_EVENT_ROUTING_KEY = "event-request.update.rk";
    private static final String DELETE_EVENT_ROUTING_KEY = "event-request.delete.rk";
    private static final String EVENT_REQUEST_DEAD_QUEUE = "event-request.dead-letter.queue";

    public static final String CREATE_EVENT_REQUEST_QUEUE = "event-request.on-create.queue";
    public static final String UPDATE_EVENT_REQUEST_QUEUE = "event-request.on-update.queue";
    public static final String DELETE_EVENT_REQUEST_QUEUE = "event-request.on-delete.queue";

    @Bean
    public Queue createEventRequestQueue() {
        return new Queue(CREATE_EVENT_REQUEST_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue updateEventRequestQueue() {
        return new Queue(UPDATE_EVENT_REQUEST_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue deleteEventRequestQueue() {
        return new Queue(DELETE_EVENT_REQUEST_QUEUE, true, false, false, deadLetterQueueArgs());
    }

    @Bean
    public Queue deadLetterEventRequestQueue() {
        return new Queue(EVENT_REQUEST_DEAD_QUEUE, true, false, false);
    }

    @Bean
    public Exchange eventRequestExchange() {
        return ExchangeBuilder
                .topicExchange(EVENT_REQUEST_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding createEventRequestBinding() {
        return BindingBuilder
                .bind(createEventRequestQueue())
                .to(eventRequestExchange())
                .with(CREATE_EVENT_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding updateEventRequestBinding() {
        return BindingBuilder
                .bind(updateEventRequestQueue())
                .to(eventRequestExchange())
                .with(UPDATE_EVENT_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding deleteEventRequestBinding() {
        return BindingBuilder
                .bind(deleteEventRequestQueue())
                .to(eventRequestExchange())
                .with(DELETE_EVENT_ROUTING_KEY)
                .noargs();
    }

    private Map<String, Object> deadLetterQueueArgs() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", EVENT_REQUEST_DEAD_QUEUE);
        return args;
    }
}
