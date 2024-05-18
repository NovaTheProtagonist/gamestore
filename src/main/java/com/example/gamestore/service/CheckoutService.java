package com.example.gamestore.service;

import java.util.ArrayList;

import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.User;
import com.example.gamestore.exception.InsufficientBalanceException;
import com.example.gamestore.repository.GameRepository;
import com.example.gamestore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CheckoutService {
    private UserRepository userRepository;
    private GameRepository gameRepository;

    @Transactional
    public Float deposit(Long userId, Float amount) {
        User user = userRepository.findById(userId).orElseThrow();
        float newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        return newBalance;
    }

    public void buyGame(Long userId, Long gameId) {
        User user = userRepository.findById(userId).orElseThrow();
        Game game = gameRepository.findById(gameId).orElseThrow();
        if (user.getBalance() < game.getPrice()) {
            throw new InsufficientBalanceException();
        }

        addGameToUser(user, game);
    }

    @Transactional
    private void addGameToUser(User user, Game game) {
        ArrayList<Game> newLibrary = new ArrayList<>(user.getGameLibrary());
        newLibrary.add(game);
        user.setGameLibrary(newLibrary);
    }
}
