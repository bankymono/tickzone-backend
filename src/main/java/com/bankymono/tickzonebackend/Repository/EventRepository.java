package com.bankymono.tickzonebackend.Repository;

import com.bankymono.tickzonebackend.Entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findByPublish(Boolean publish);
}
