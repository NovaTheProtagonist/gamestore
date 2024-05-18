package com.example.gamestore.service;

import java.util.Optional;

import com.example.gamestore.entity.User;
import com.example.gamestore.model.mapper.UserMapper;
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

    public User handleRegister(RegisterRequest registerRequest) {
        User user = UserMapper.mapRegisterRequestToUser(registerRequest);
        user.setStatus(User.UserStatus.ONLINE);
        return userRepository.save(user);
    }

    public User handleLogin(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        User userEntity = user.orElseThrow();
        userEntity.setStatus(User.UserStatus.ONLINE);
        return userEntity;
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
