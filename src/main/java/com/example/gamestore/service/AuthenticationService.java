package com.example.gamestore.service;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.request.LoginRequest;
import com.example.gamestore.model.request.RegisterRequest;
import com.example.gamestore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserRepository userRepository;

    public void handleRegister(RegisterRequest registerRequest) {

    }

    public void handleLogin(LoginRequest loginRequest) {

    }

    public void handleLogout(Long userId) {
        setUserOffline(userId);
    }

    @Transactional
    private void setUserOffline(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setStatus(User.UserStatus.OFFLINE);
    }
}
