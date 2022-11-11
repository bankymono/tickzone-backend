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

    @PostMapping()
    public ResponseEntity<Boolean> createTickets( @RequestParam("eventId") int eventId, @RequestBody BatchTickets tickets) {
        return new ResponseEntity<>(ticketService.createTickets(eventId, tickets),
                HttpStatus.CREATED);
    }

    @PutMapping("/{ticketId}/buy")
    public ResponseEntity<Ticket> buyTicket(@PathVariable("ticketId") int ticketId){
        return new ResponseEntity<>(ticketService.buyTicket(ticketId), HttpStatus.OK);
    }

}
