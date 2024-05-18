package com.example.gamestore.exception;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String username) {
        super("Username " + username + " has been already taken.");
    }
}


