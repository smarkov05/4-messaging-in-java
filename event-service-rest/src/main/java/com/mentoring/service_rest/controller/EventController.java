package com.mentoring.service_rest.controller;

import com.mentoring.service_api.EventService;
import com.mentoring.service_dto.model.Event;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    @ApiOperation("Getting a particular event by id")
    public Event getEvent(@PathVariable("id") String id) {
        return eventService.getEvent(Long.parseLong(id));
    }

    @GetMapping
    @ApiOperation("Getting all existing events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    @ApiOperation("Creating a new event")
    public Event createEvent(@RequestBody Event event) {


        return eventService.createEvent(event);
    }

    @PutMapping
    @ApiOperation("Updating the event")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleting the event by id")
    public void deleteEvent(@PathVariable("id") String id) {
        eventService.deleteEvent(Long.parseLong(id));
    }
}

