package com.example.gamestore.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BuyGameRequest {
    private Long userId;
    private Long gameId;
}
