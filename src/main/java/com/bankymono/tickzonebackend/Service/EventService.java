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

    public List<Event> getPublishEvents(){
        return (List<Event>) eventRepository.findByPublish(true);
    }

    public Event getEvent(int eventId) {
        Optional<Event> event = eventRepository.findById(eventId);

        if(event.isPresent()){
            return event.get();
        }else
            throw new EntityNotFoundException(event.get().getId(), User.class);
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

    public Event publishEvent(int eventId) {
        System.out.println("mo ti de be");
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            event.get().setPublish(true);
            return eventRepository.save(event.get());
        }else {
            throw new EntityNotFoundException(eventId,Event.class);
        }
    }

    public  List<Ticket> getEventTickets(int eventId) {
        Event event  = getEvent(eventId);
        return event.getTickets();
    }
}
