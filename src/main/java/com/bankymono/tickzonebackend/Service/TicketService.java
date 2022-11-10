package com.bankymono.tickzonebackend.Service;

import com.bankymono.tickzonebackend.Entity.BatchTickets;
import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Ticket;
import com.bankymono.tickzonebackend.Entity.User;
import com.bankymono.tickzonebackend.Exception.EntityNotFoundException;
import com.bankymono.tickzonebackend.Exception.UserNotFoundException;
import com.bankymono.tickzonebackend.Repository.EventRepository;
import com.bankymono.tickzonebackend.Repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;

    private EventRepository eventRepository;


    public List<Ticket> getAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Ticket getTicket(int ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);

        if(ticket.isPresent()){
            return ticket.get();
        }else
            throw new EntityNotFoundException(ticket.get().getId(), User.class);
    }

    public Ticket saveTicket(int eventId, Ticket ticket) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            ticket.setEvent(event.get());
        }else {
            throw new EntityNotFoundException(eventId, Event.class);
        }
        return ticketRepository.save(ticket);
    }

    public Boolean createTickets( int eventId, BatchTickets tickets) {
        ArrayList<Ticket> eventTickets = new ArrayList<>();
        Event theEvent =  eventRepository.findById(eventId).get();


        for(int i = 0; i < tickets.getNoOfTickets(); ++i){
            Ticket newTicket = new Ticket();
            newTicket.setTicketName(tickets.getName());
            newTicket.setTicketPrice(tickets.getPrice());
            newTicket.setInfo(tickets.getInfo());
            newTicket.setUniqueCode(UUID.randomUUID().toString());
            newTicket.setEvent(theEvent);

            eventTickets.add(newTicket);
        }

        ticketRepository.saveAll(eventTickets);
        return true;
    }

    public Ticket buyTicket(int ticketId, Boolean purchase) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(ticket.isPresent()) {
            ticket.get().setPurchased(true);
            return ticketRepository.save(ticket.get());
        }else
            throw new EntityNotFoundException(ticketId, Ticket.class);

    }

}
