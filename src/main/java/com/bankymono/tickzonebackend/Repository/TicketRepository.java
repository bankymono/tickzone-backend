package com.bankymono.tickzonebackend.Repository;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByEventAndPurchased(Event event, Boolean purchased);
}
