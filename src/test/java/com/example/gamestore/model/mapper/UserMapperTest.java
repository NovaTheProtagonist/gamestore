package com.example.gamestore.model.mapper;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.RegisterRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void mapRegisterRequestToUser() {
        //given
        RegisterRequest registerRequest = RegisterRequest.builder().username("asd").password("123").build();
        User expectedUser = new User();
        expectedUser.setRole(User.Role.USER);
        expectedUser.setGameLibrary(new ArrayList<>());
        expectedUser.setUsername(registerRequest.getUsername());
        expectedUser.setPassword(registerRequest.getPassword());
        expectedUser.setBalance(0.0f);
        //when
        User actualUser = UserMapper.mapRegisterRequestToUser(registerRequest);
        //then
        assertEquals(expectedUser, actualUser);
    }
}