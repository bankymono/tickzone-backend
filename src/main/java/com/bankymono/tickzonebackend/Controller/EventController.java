package com.bankymono.tickzonebackend.Controller;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Response;
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

@CrossOrigin(origins = "*")
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

    @GetMapping("/user/{eventId}")
    public ResponseEntity<Event> findGenEvent(@PathVariable int eventId) {
        return new ResponseEntity<>(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable int eventId, @RequestBody Event event) {
        return new ResponseEntity<>(eventService.updateEvent(eventId, event), HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Response> deleteEvent(@PathVariable int eventId) {
        eventService.deleteEvent(eventId);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");
        return new ResponseEntity<>(response,HttpStatus.OK);
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

    @PutMapping("/{eventId}/upload")
    public ResponseEntity<Event> uploadEventImage(@PathVariable int eventId, @RequestBody String imageUrl) {
        return new ResponseEntity<>(eventService.uploadEventImage(eventId, imageUrl), HttpStatus.OK);
    }

}
