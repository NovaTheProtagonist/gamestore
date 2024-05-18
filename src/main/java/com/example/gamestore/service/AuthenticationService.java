package com.example.gamestore.service;

import java.util.Optional;

import com.example.gamestore.entity.User;
import com.example.gamestore.exception.IncorrectPasswordException;
import com.example.gamestore.exception.UserNotFoundException;
import com.example.gamestore.exception.UsernameTakenException;
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
        if (userRepository.findUserByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameTakenException(registerRequest.getUsername());
        }
        User user = UserMapper.mapRegisterRequestToUser(registerRequest);
        user.setStatus(User.UserStatus.ONLINE);
        return userRepository.save(user);
    }

    public User handleLogin(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findUserByUsername(loginRequest.getUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundException(loginRequest.getUsername());
        }
        if (!user.get().getPassword().equals(loginRequest.getPassword())) {
            throw new IncorrectPasswordException();
        }
        setUserStatus(user.get().getId(), User.UserStatus.ONLINE);
        return user.get();
    }

    public void handleLogout(Long userId) {
        setUserStatus(userId, User.UserStatus.OFFLINE);
    }

    @Transactional
    private void setUserStatus(Long userId, User.UserStatus userStatus) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setStatus(userStatus);
    }
}
