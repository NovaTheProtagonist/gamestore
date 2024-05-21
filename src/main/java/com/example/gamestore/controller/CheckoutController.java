package com.example.gamestore.controller;

import com.example.gamestore.model.request.BuyGameRequest;
import com.example.gamestore.model.request.DepositRequest;
import com.example.gamestore.model.response.DepositResponse;
import com.example.gamestore.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping(value = "/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequest depositRequest) {
        Float newBalance = checkoutService.deposit(depositRequest.getUserId(), depositRequest.getAmount());
        DepositResponse response = DepositResponse.builder().newBalance(newBalance).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Void> buyGame(@RequestBody BuyGameRequest buyGameRequest) {
        checkoutService.buyGame(buyGameRequest.getUserId(), buyGameRequest.getGameId());
        return ResponseEntity.ok().build();
    }

}
