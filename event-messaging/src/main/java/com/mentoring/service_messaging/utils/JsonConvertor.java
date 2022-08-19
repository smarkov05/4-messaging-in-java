package com.mentoring.service_messaging.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoring.service_dto.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonConvertor {

    private final ObjectMapper mapper;

    public String convertToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new SerializationFailedException("Can't serialize object. Object: %s.".formatted(obj));
        }
    }

    public Event convertToEvent(String eventJson) {
        try {
            return mapper.readValue(eventJson, Event.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new SerializationFailedException("Can't serialize object. Object: %s.".formatted(eventJson));
        }
    }
}
