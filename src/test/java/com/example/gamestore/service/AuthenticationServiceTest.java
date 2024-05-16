package com.example.gamestore.service;

import com.example.gamestore.entity.User;
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
}