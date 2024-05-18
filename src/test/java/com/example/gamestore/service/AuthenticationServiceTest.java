package com.example.gamestore.service;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.LoginRequest;
import com.example.gamestore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    AuthenticationService authenticationService;
    @Test
    void handleLogoutWillLookUpUserWithValidId() {
        //given
        Long userId = 123L;
        User user = new User();
        user.setId(1L);
        user.setStatus(User.UserStatus.ONLINE);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of((user)));
        //when
        authenticationService.handleLogout(userId);
        //then
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        assertEquals(user.getStatus(),User.UserStatus.OFFLINE);
    }

    @Test
    void handleLoginWillSetUserToOnlineWhenRequestIsValid() {
        //given
        User expecteduser = new User();
        expecteduser.setId(1L);
        expecteduser.setStatus(User.UserStatus.OFFLINE);
        expecteduser.setUsername("gizel");
        expecteduser.setPassword("1q2w3e");
        LoginRequest loginRequest = LoginRequest.builder()
                .username(expecteduser.getUsername())
                .password(expecteduser.getPassword())
                .build();
        Mockito.when(userRepository.findUserByUsername(expecteduser.getUsername()))
                .thenReturn(Optional.of((expecteduser)));
        Mockito.when(userRepository.findById(expecteduser.getId()))
                .thenReturn(Optional.of((expecteduser)));
        //when
        User user = authenticationService.handleLogin(loginRequest);
        //then
        Mockito.verify(userRepository, Mockito.times(1))
                .findUserByUsername(expecteduser.getUsername());
        assertEquals(user.getStatus(),User.UserStatus.ONLINE);
        assertEquals(user.getUsername(), expecteduser.getUsername());
        assertEquals(user.getRole(), expecteduser.getRole());
        assertEquals(user.getPassword(), expecteduser.getPassword());
        assertEquals(user.getGameLibrary(), expecteduser.getGameLibrary());
    }
}