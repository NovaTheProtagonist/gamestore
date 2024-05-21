package com.example.gamestore.controller;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.LoginRequest;
import com.example.gamestore.model.response.LoginResponse;
import com.example.gamestore.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    AuthenticationController controller;

    @Test
    void logoutWillCallServiceMethodWhenUserIdIsValid() {
        //given
        Long userId = 123L;
        //when
        controller.logout(userId);
        //then
        Mockito.verify(authenticationService, Mockito.times(1)).handleLogout(userId);
    }

    @Test
    void loginWillCallServiceMethodWhenRequestIsValid() {
        //given
        User user = new User();
        user.setId(1L);
        user.setUsername("almos");
        user.setRole(User.Role.USER);
        user.setPassword("nagyon");
        LoginRequest loginRequest = LoginRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        LoginResponse expectedResponse = LoginResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .userId(user.getId())
                .build();
        Mockito.when(authenticationService.handleLogin(loginRequest)).thenReturn(user);
        //when
        ResponseEntity<LoginResponse> loginResponse = controller.login(loginRequest);
        //then
        Mockito.verify(authenticationService, Mockito.times(1)).handleLogin(loginRequest);
        assertEquals(loginResponse.getBody(), expectedResponse);
    }
}
