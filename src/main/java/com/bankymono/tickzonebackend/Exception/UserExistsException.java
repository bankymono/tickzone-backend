package com.bankymono.tickzonebackend.Exception;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String email){
        super("The User with " + email + " already exists");
    }
}
