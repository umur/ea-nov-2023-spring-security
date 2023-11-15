package edu.miu.springsecurity1.service;

import edu.miu.springsecurity1.entity.User;
import edu.miu.springsecurity1.entity.dto.request.LoginRequest;
import edu.miu.springsecurity1.entity.dto.request.SignupDto;
import edu.miu.springsecurity1.entity.dto.response.LoginResponse;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.entity.dto.response.SignupResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    SignupResponse signup(SignupDto loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    User getCurrentlyLoggedInUser();
}
