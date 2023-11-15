package com.ea.dataone.service.impl;

import com.ea.dataone.dto.LoginRequest;
import com.ea.dataone.dto.LoginResponse;
import com.ea.dataone.dto.RefreshTokenRequest;
import com.ea.dataone.dto.RegisterRequest;
import com.ea.dataone.entity.Role;
import com.ea.dataone.entity.User;
import com.ea.dataone.repository.UserRepo;
import com.ea.dataone.service.AuthService;
import com.ea.dataone.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public LoginResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstname());
        user.setLastName(registerRequest.getLastname());
        user.getAddress().setStreet(registerRequest.getStreet());
        user.getAddress().setCity(registerRequest.getCity());
        user.getAddress().setZip(registerRequest.getZip());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role role = new Role();
        role.setRole(registerRequest.getRole());
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
        return new LoginResponse();
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if(isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew is this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            final String accessToken = jwtUtil.doGenerateToken(  jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new LoginResponse();
    }
}
