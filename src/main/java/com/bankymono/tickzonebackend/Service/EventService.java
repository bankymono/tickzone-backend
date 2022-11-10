package com.bankymono.tickzonebackend.Service;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Ticket;
import com.bankymono.tickzonebackend.Entity.User;
import com.bankymono.tickzonebackend.Exception.EntityNotFoundException;
import com.bankymono.tickzonebackend.Exception.UserNotFoundException;
import com.bankymono.tickzonebackend.Repository.EventRepository;
import com.bankymono.tickzonebackend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;


    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public List<Event> getPublishEvents(Boolean publish){
        return (List<Event>) eventRepository.findByPublish(publish);
    }

    public Event saveEvent(String email, Event event) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            event.setUser(user.get());
        }else {
            throw new UserNotFoundException(email);
        }
        return eventRepository.save(event);
    }

    public Event uploadEventImage(int eventId, String imageUrl) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            event.get().setImageUrl(imageUrl);
            return eventRepository.save(event.get());
        }else {
            throw new EntityNotFoundException(eventId,Event.class);
        }
    }

    public Event publishEvent(int eventId, Boolean publish) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            event.get().setPublish(publish);
            return eventRepository.save(event.get());
        }else {
            throw new EntityNotFoundException(eventId,Event.class);
        }
    }

    public  List<Event> getEventTickets(int eventId) {
        Optional<Event> event  = eventRepository.findById(eventId);

        return event.get().getTickets();
    }
}
