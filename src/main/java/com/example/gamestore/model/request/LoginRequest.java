package com.example.gamestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
