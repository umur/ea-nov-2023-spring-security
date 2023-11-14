package com.ea.dataone.service;

import com.ea.dataone.dto.LoginRequest;
import com.ea.dataone.dto.LoginResponse;
import com.ea.dataone.dto.RefreshTokenRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
