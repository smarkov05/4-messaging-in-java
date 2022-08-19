package com.mentoring.service_messaging.kafka;

import com.mentoring.service_api.EventMessaging;
import com.mentoring.service_dto.model.Event;
import com.mentoring.service_messaging.kafka.config.KafkaEventTopicConfig;
import com.mentoring.service_messaging.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaEventProducer implements EventMessaging {

    private final JsonConvertor jsonConvertor;
    private final KafkaTemplate<String, String> template;


    @Override
    public void createEvent(Event event) {
        template.send(KafkaEventTopicConfig.CREATE_EVENT_NOTIFICATION_TOPIC, jsonConvertor.convertToJson(event));
    }

    @Override
    public void updateEvent(Event event) {
        template.send(KafkaEventTopicConfig.UPDATE_EVENT_NOTIFICATION_TOPIC, jsonConvertor.convertToJson(event));
    }

    @Override
    public void delete(Long id) {
        template.send(KafkaEventTopicConfig.DELETE_EVENT_NOTIFICATION_TOPIC, id.toString());
    }
}
