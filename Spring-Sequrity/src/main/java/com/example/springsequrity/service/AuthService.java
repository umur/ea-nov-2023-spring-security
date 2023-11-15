package com.example.springsequrity.service;

import com.example.springsequrity.entity.dto.response.LoginRes;
import com.example.springsequrity.entity.User;
import com.example.springsequrity.entity.dto.request.LoginRequest;
import com.example.springsequrity.entity.dto.request.RegistrationRequest;
import com.example.springsequrity.entity.dto.response.LoginRes;
import com.example.springsequrity.entity.dto.request.RefreshTokenRequest;
import com.example.springsequrity.entity.dto.response.RegistrationRes;

public interface AuthService {
    LoginRes login(LoginRequest loginRequest);

    RegistrationRes registration(RegistrationRequest loginRequest);
    LoginRes refreshToken(RefreshTokenRequest refreshTokenRequest);

    User getCurrentlyLoggedInUser();
}
