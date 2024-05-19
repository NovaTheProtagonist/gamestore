package com.example.gamestore.service;

import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.User;
import com.example.gamestore.exception.InsufficientBalanceException;
import com.example.gamestore.repository.GameRepository;
import com.example.gamestore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    GameRepository gameRepository;

    @InjectMocks
    CheckoutService checkoutService;

    @Test
    void depositWillAddAmountToUserBalance() {
        User user = new User();
        user.setBalance(100f);
        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user));
        float balance = checkoutService.deposit(user.getId(), 50f);
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(user.getId());
        assertEquals(150f, balance);
        assertEquals(balance, user.getBalance());
    }

    @Test
    void buyGameWillAddGameToUserLibraryWhenUserHasEnoughFunds() {
        User user = new User();
        Game game = new Game();
        game.setName("Sas");
        user.setGameLibrary(new ArrayList<>());
        game.setPrice(10f);
        user.setBalance(11f);
        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user));
        Mockito.when(gameRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(game));
        checkoutService.buyGame(user.getId(), game.getId());
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(user.getId());
        Mockito.verify(gameRepository, Mockito.times(1))
                .findById(game.getId());
        assertTrue(user.getGameLibrary().contains(game));
        assertEquals(1f, user.getBalance());
    }

    @Test
    void buyGameWillNotAddGameToUserLibraryWhenUserDoesntHaveEnoughFunds() {
        User user = new User();
        Game game = new Game();
        game.setName("Sas");
        user.setGameLibrary(new ArrayList<>());
        game.setPrice(10f);
        user.setBalance(9f);
        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user));
        Mockito.when(gameRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(game));
        assertThrows(InsufficientBalanceException.class, () -> checkoutService.buyGame(user.getId(), game.getId()));
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(user.getId());
        Mockito.verify(gameRepository, Mockito.times(1))
                .findById(game.getId());
    }
}