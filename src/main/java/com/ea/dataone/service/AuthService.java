package com.ea.dataone.service;

import com.ea.dataone.dto.LoginRequest;
import com.ea.dataone.dto.LoginResponse;
import com.ea.dataone.dto.RefreshTokenRequest;
import com.ea.dataone.dto.RegisterRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegisterRequest registerRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
