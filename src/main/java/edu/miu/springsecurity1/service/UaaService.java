package edu.miu.springsecurity1.service;

import edu.miu.springsecurity1.entity.dto.request.SigninDtoRequest;
import edu.miu.springsecurity1.entity.dto.request.SignupDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.SigninDtoResponse;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.entity.dto.response.SignupDtoResponse;

public interface UaaService {
    SigninDtoResponse signin(SigninDtoRequest signinDtoRequest);
    SigninDtoResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    SignupDtoResponse signup(SignupDtoRequest signupDtoRequest);
}
