package com.example.gamestore.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private Long userId;
    private String username;
    private String role;
}
