package com.bankymono.tickzonebackend.Controller;

import com.bankymono.tickzonebackend.Entity.BatchTickets;
import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Ticket;
import com.bankymono.tickzonebackend.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> findAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable int ticketId){
        return new ResponseEntity<>(ticketService.getTicket(ticketId), HttpStatus.OK);
    }

    @PostMapping("/create/{eventId}")
    public ResponseEntity<Boolean> createTickets( @PathVariable int eventId, @RequestBody BatchTickets tickets) {
        System.out.println(eventId + ", " + tickets.toString() + " heee");
        return new ResponseEntity<>(ticketService.createTickets(eventId, tickets),
                HttpStatus.CREATED);
    }

    @PutMapping("/{ticketId}/buy")
    public ResponseEntity<Ticket> buyTicket(@PathVariable int ticketId){
        return new ResponseEntity<>(ticketService.buyTicket(ticketId), HttpStatus.OK);
    }

    @GetMapping("/{eventId}/available")
    public ResponseEntity<List<Ticket>> getAvailableTickets(@PathVariable int eventId){
        return  new ResponseEntity<>(ticketService.getAvailableTickets(eventId), HttpStatus.OK);
    }

}
