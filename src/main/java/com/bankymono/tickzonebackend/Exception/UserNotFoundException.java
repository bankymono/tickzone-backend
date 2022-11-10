package com.bankymono.tickzonebackend.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email){
        super("The User with " + email + " already exists");
    }
}
