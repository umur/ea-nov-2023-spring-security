package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.User;
import edu.miu.springsecurity1.entity.dto.request.SigninDtoRequest;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.entity.dto.request.SignupDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.SigninDtoResponse;
import edu.miu.springsecurity1.entity.dto.response.SignupDtoResponse;
import edu.miu.springsecurity1.repository.UserRepo;
import edu.miu.springsecurity1.service.UaaService;
import edu.miu.springsecurity1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public SigninDtoResponse signin(SigninDtoRequest signinDtoRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinDtoRequest.getEmail(), signinDtoRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(signinDtoRequest.getEmail());
        var loginResponse = new SigninDtoResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public SigninDtoResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew is this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new SigninDtoResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new SigninDtoResponse();
    }

    @Override
    public SignupDtoResponse signup(SignupDtoRequest signupDtoRequest) {
        User newUser = modelMapper.map(signupDtoRequest, User.class);
        newUser.setId(null);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        var savedUser = userRepo.save(newUser);
        return modelMapper.map(savedUser, SignupDtoResponse.class);
    }
}