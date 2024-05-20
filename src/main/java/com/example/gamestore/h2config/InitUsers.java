package com.example.gamestore.h2config;

import com.example.gamestore.entity.User;
import com.example.gamestore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitUsers implements CommandLineRunner {
    private UserRepository userRepository;

    @Transactional
    private void initTestUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole(User.Role.USER);
        user.setBalance(0f);
        userRepository.save(user);
    }

    @Transactional
    private void initTestAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRole(User.Role.ADMIN);
        user.setBalance(0f);
        userRepository.save(user);
    }

    @Override
    public void run(String... args) throws Exception {
        initTestUser();
        initTestAdmin();
    }
}
