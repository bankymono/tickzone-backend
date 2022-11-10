package com.bankymono.tickzonebackend.Repository;

import com.bankymono.tickzonebackend.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
