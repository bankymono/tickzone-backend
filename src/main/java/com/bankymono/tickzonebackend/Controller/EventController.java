package com.bankymono.tickzonebackend.Controller;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Ticket;
import com.bankymono.tickzonebackend.Entity.User;
import com.bankymono.tickzonebackend.Service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findEvent(@PathVariable int eventId) {
        return new ResponseEntity<>(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Event>> findPublishedEvents( ) {
        return new ResponseEntity<>(eventService.getPublishEvents(), HttpStatus.OK);
    }

    @GetMapping("/{eventId}/tickets")
    public ResponseEntity<List<Ticket>> getEventTickets(@PathVariable int eventId) {
        return new ResponseEntity<>(eventService.getEventTickets(eventId),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, Authentication authentication) {
        return new ResponseEntity<>(eventService.saveEvent(authentication.getName(), event),
                HttpStatus.CREATED);
    }



    @PutMapping("/publish/{eventId}")
    public ResponseEntity<Event> publishEvent(@PathVariable int eventId) {
        return new ResponseEntity<>(eventService.publishEvent(eventId), HttpStatus.OK);
    }


}
