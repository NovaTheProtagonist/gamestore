package com.example.gamestore.controller;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.LoginRequest;
import com.example.gamestore.model.request.RegisterRequest;
import com.example.gamestore.model.response.LoginResponse;
import com.example.gamestore.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest registerRequest) {
        LoginResponse loginResponse = LoginResponse.builder()
                .userId(0L)
                .role(User.Role.USER.name())
                .username("")
                .build();
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = LoginResponse.builder()
                .userId(0L)
                .role(User.Role.USER.name())
                .username("")
                .build();
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping(value = "/logout/{userId}")
    public ResponseEntity<Void> logout(@RequestParam Long userId) {
        authenticationService.handleLogout(userId);
        return ResponseEntity.ok().build();
    }
}
