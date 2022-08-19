package com.mentoring.service_messaging.kafka.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("kafka")
@RequiredArgsConstructor
public class KafkaEventTopicConfig {
    private static final Integer DEFAULT_REPLICAS_QUANTITY = 1;
    private static final Integer DEFAULT_PARTITION_QUANTITY = 2;

    public static final String CREATE_EVENT_NOTIFICATION_TOPIC = "create.event.notification.topic";
    public static final String UPDATE_EVENT_NOTIFICATION_TOPIC = "update.event.notification.topic";
    public static final String DELETE_EVENT_NOTIFICATION_TOPIC = "delete.event.notification.topic";

    public static final String CREATE_EVENT_REQUEST_TOPIC = "create.event.request.topic";
    public static final String UPDATE_EVENT_REQUEST_TOPIC = "update.event.request.topic";
    public static final String DELETE_EVENT_REQUEST_TOPIC = "delete.event.request.topic";

    @Bean
    public NewTopic createEventNotificationTopic() {
        return TopicBuilder
                .name(CREATE_EVENT_NOTIFICATION_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }

    @Bean
    public NewTopic updateEventNotificationTopic() {
        return TopicBuilder
                .name(UPDATE_EVENT_NOTIFICATION_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }

    @Bean
    public NewTopic deleteEventNotificationTopic() {
        return TopicBuilder
                .name(DELETE_EVENT_NOTIFICATION_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }

    @Bean
    public NewTopic createEventRequestTopic() {
        return TopicBuilder
                .name(CREATE_EVENT_REQUEST_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }

    @Bean
    public NewTopic updateEventRequestTopic() {
        return TopicBuilder
                .name(UPDATE_EVENT_REQUEST_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }

    @Bean
    public NewTopic deleteEventRequestTopic() {
        return TopicBuilder
                .name(DELETE_EVENT_REQUEST_TOPIC)
                .replicas(DEFAULT_REPLICAS_QUANTITY)
                .partitions(DEFAULT_PARTITION_QUANTITY)
                .build();
    }
}
