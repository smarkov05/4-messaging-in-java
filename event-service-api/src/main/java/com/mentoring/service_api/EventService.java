package com.mentoring.service_api;

import com.mentoring.service_dto.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    Event updateEvent(Event event);

    Event getEvent(Long id);

    void deleteEvent(Long id);

    List<Event> getAllEvents();

}
