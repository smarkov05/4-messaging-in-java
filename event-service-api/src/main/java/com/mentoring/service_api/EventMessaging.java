package com.mentoring.service_api;

import com.mentoring.service_dto.model.Event;

public interface EventMessaging {
    void createEvent(Event event);

    void updateEvent(Event event);

    void delete(Long id);
}
