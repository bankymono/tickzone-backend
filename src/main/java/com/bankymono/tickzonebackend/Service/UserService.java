package com.bankymono.tickzonebackend.Service;

import com.bankymono.tickzonebackend.Entity.Event;
import com.bankymono.tickzonebackend.Entity.User;
import com.bankymono.tickzonebackend.Exception.EntityNotFoundException;
import com.bankymono.tickzonebackend.Exception.UserExistsException;
import com.bankymono.tickzonebackend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            return user.get();
        }else
            throw new EntityNotFoundException(user.get().getId(), User.class);
    }


    public User saveUser(User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()) {
            throw new UserExistsException(user.getEmail());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public  List<Event> getUserEvents(String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);

        return user.get().getEvents();

    }

    public void deleteUser(int id) throws UserExistsException{

        userRepository.deleteById(id);
    }




}
