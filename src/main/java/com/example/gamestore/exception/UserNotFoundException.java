package com.example.gamestore.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with this username " + username + " does not exist.");
    }
}

