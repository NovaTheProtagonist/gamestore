package com.example.gamestore.controller;

import com.example.gamestore.repository.UserRepository;
import com.example.gamestore.service.AuthenticationService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    AuthenticationController controller;

    @Test
    void logoutWillSetUserToOfflineWhenUserIdIsValid() {
        //given
        Long userId = 123L;
        //when
        controller.logout(userId);
        //then
        Mockito.verify(authenticationService, Mockito.times(1)).handleLogout(userId);
    }
}