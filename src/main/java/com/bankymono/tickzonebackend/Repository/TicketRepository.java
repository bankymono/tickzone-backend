package com.bankymono.tickzonebackend.Repository;

import com.bankymono.tickzonebackend.Entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
