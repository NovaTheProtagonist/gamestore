package com.example.gamestore.service;

import com.example.gamestore.entity.User;
import com.example.gamestore.exception.IncorrectPasswordException;
import com.example.gamestore.exception.UserNotFoundException;
import com.example.gamestore.exception.UsernameTakenException;
import com.example.gamestore.model.request.LoginRequest;
import com.example.gamestore.model.request.RegisterRequest;
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

    @Test
    void handleRegisterWillCreateNewUserAndLogThemIn() {
        //given
        User expecteduser = new User();
        expecteduser.setId(1L);
        expecteduser.setStatus(User.UserStatus.ONLINE);
        expecteduser.setUsername("gizel");
        expecteduser.setPassword("1q2w3e");
        expecteduser.setBalance(0.0f);
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username(expecteduser.getUsername())
                .password(expecteduser.getPassword())
                .build();
        Mockito.when(userRepository.findUserByUsername(expecteduser.getUsername()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(expecteduser);
        //when
        User user = authenticationService.handleRegister(registerRequest);
        //then
        Mockito.verify(userRepository, Mockito.times(1))
                .findUserByUsername(expecteduser.getUsername());
        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any());
        assertEquals(user.getStatus(),User.UserStatus.ONLINE);
        assertEquals(user.getUsername(), expecteduser.getUsername());
        assertEquals(user.getRole(), expecteduser.getRole());
        assertEquals(user.getPassword(), expecteduser.getPassword());
        assertEquals(user.getGameLibrary(), expecteduser.getGameLibrary());
    }

    @Test
    void handleRegisterWillThrowExceptionWhenUsernameIsTaken() {
        //given
        RegisterRequest registerRequest = RegisterRequest.builder().username("kesudio").password("sa12").build();
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        Mockito.when(userRepository.findUserByUsername(registerRequest.getUsername()))
                .thenReturn(Optional.of(user));
        assertThrows(UsernameTakenException.class, ()->authenticationService.handleRegister(registerRequest));
    }

    @Test
    void handleLoginWillThrowExceptionWhenUserDoesNotExist() {
        //given
        LoginRequest loginRequest = LoginRequest.builder().username("kesudio").password("sa12").build();
        Mockito.when(userRepository.findUserByUsername(loginRequest.getUsername()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()->authenticationService.handleLogin(loginRequest));
    }

    @Test
    void handleLoginWillThrowExceptionWhenPasswordIsIncorrect() {
        //given
        LoginRequest loginRequest = LoginRequest.builder().username("kesudio").password("sa12").build();
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword("sa13");
        Mockito.when(userRepository.findUserByUsername(loginRequest.getUsername()))
                .thenReturn(Optional.of(user));
        assertThrows(IncorrectPasswordException.class, ()->authenticationService.handleLogin(loginRequest));
    }
}