package com.mentoring.service_impl.repository;

import com.mentoring.service_dto.model.Event;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class MockEventRepository {
    private static final AtomicLong ID = new AtomicLong(0);
    private static final  Map<Long, Event> EVENTS = new ConcurrentHashMap<>();


    private MockEventRepository() {
    }

    public static Event addEvent(Event event) {
        event.setId(ID.getAndIncrement());
        EVENTS.put(event.getId(), event);
        return getEvent(event.getId());
    }

    public static Event updateEvent(Event event) {
        Event toUpdate = EVENTS.get(event.getId());
        if (Objects.isNull(toUpdate)) {
            throw new NoSuchElementException("Can't update event by id: %d. Event doesn't exist ".formatted(event.getId()));
        }
        Optional.ofNullable(event.getTitle()).ifPresent(toUpdate::setTitle);
        Optional.ofNullable(event.getSpeaker()).ifPresent(toUpdate::setSpeaker);
        Optional.ofNullable(event.getPlace()).ifPresent(toUpdate::setPlace);
        Optional.ofNullable(event.getType()).ifPresent(toUpdate::setType);
        Optional.ofNullable(event.getDateTime()).ifPresent(toUpdate::setDateTime);
        return EVENTS.put(event.getId(), event);
    }


    public static void deleteEvent(Long id) {
        Event toDelete = EVENTS.get(id);
        if (Objects.isNull(toDelete)) {
            throw new NoSuchElementException("Can't delete event by id: %d. Event doesn't exist ".formatted(id));
        }
        EVENTS.remove(id);
    }

    public static List<Event> getAllEvents() {
        return EVENTS.values().stream().toList();
    }

    public static Event getEvent(Long id) {
        return EVENTS.get(id);
    }
}
