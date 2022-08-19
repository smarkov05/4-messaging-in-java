package com.mentoring.service_impl.service;

import com.mentoring.service_api.EventMessaging;
import com.mentoring.service_api.EventService;
import com.mentoring.service_dto.model.Event;
import com.mentoring.service_impl.repository.EventRepository;
import com.mentoring.service_impl.repository.MockEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMessaging eventMessaging;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
//        Event savedEvent = MockEventRepository.addEvent(event);
        Event savedEvent = eventRepository.save(event);
        eventMessaging.createEvent(savedEvent);
        return savedEvent;
    }

    @Override
    public Event updateEvent(Event event) {
        Event updatedEvent = MockEventRepository.updateEvent(event);
//        Event updatedEvent = eventRepository.save(event);
        eventMessaging.updateEvent(updatedEvent);
        return updatedEvent;
    }

    @Override
    public Event getEvent(Long id) {
        return MockEventRepository.getEvent(id);
//        return eventRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Can't find event by id: %d".formatted(id)));
    }

    @Override
    public void deleteEvent(Long id) {
        MockEventRepository.deleteEvent(id);
//        eventRepository.deleteById(id);
        eventMessaging.delete(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return MockEventRepository.getAllEvents();
//        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
//                .toList();
    }
}
