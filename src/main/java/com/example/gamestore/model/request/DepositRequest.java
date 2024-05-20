package com.example.gamestore.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepositRequest {
    private Long userId;
    private Float amount;
}
