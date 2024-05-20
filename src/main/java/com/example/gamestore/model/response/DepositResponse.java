package com.example.gamestore.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepositResponse {
    private Float newBalance;
}
