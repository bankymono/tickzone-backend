package com.bankymono.tickzonebackend.service;

import com.bankymono.tickzonebackend.entity.User;
import com.bankymono.tickzonebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
