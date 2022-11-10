package com.bankymono.tickzonebackend.Controller;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.User;
import com.bankymono.tickzonebackend.Service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/publish")
    public ResponseEntity<List<Event>> findPublishEvents(@RequestParam("publishVar") Boolean publishVar) {
        return new ResponseEntity<>(eventService.getPublishEvents(publishVar), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, Authentication authentication) {
        return new ResponseEntity<>(eventService.saveEvent(authentication.getName(), event),
                HttpStatus.CREATED);
    }
}
