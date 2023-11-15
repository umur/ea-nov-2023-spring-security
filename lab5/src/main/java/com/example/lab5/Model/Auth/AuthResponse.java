package com.example.lab5.Model.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

@AllArgsConstructor
public class AuthResponse {
    private String email;
    private String accessToken;
}
