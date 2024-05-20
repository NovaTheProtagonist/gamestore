package com.example.gamestore.controller;

import com.example.gamestore.model.request.BuyGameRequest;
import com.example.gamestore.model.request.DepositRequest;
import com.example.gamestore.model.response.DepositResponse;
import com.example.gamestore.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckoutControllerTest {

    @Mock
    CheckoutService checkoutService;
    @InjectMocks
    CheckoutController checkoutController;

    @Test
    void depositShouldCallServiceWithValidRequest() {
        DepositRequest depositRequest = DepositRequest.builder().userId(1L).amount(100f).build();
        checkoutController.deposit(depositRequest);
        Mockito.verify(checkoutService, Mockito.times(1))
                .deposit(depositRequest.getUserId(), depositRequest.getAmount());
    }

    @Test
    void buyGameShouldCallServiceWithValidRequest() {
        BuyGameRequest buyGameRequest = BuyGameRequest.builder().userId(1L).gameId(1L).build();
        checkoutController.buyGame(buyGameRequest);
        Mockito.verify(checkoutService, Mockito.times(1))
                .buyGame(buyGameRequest.getUserId(), buyGameRequest.getGameId());
    }
}