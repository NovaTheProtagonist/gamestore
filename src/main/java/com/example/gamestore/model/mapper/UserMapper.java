package com.example.gamestore.model.mapper;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.RegisterRequest;

import java.util.ArrayList;

public interface UserMapper {
    static User mapRegisterRequestToUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole(User.Role.USER);
        user.setGameLibrary(new ArrayList<>());
    }
}
