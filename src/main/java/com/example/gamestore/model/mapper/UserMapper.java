package com.example.gamestore.model.mapper;

import java.util.ArrayList;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.RegisterRequest;


public interface UserMapper {
    static User mapRegisterRequestToUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole(User.Role.USER);
        user.setGameLibrary(new ArrayList<>());
        user.setBalance(0f);
        return user;
    }
}
