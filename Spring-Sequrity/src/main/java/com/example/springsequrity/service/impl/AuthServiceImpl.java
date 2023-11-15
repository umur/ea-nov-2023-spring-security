package com.example.springsequrity.service.impl;

import com.example.springsequrity.entity.User;
import com.example.springsequrity.entity.dto.request.LoginRequest;
import com.example.springsequrity.entity.dto.request.RegistrationRequest;
import com.example.springsequrity.entity.dto.response.LoginRes;
import com.example.springsequrity.entity.dto.request.RefreshTokenRequest;
import com.example.springsequrity.entity.dto.response.RegistrationRes;
import com.example.springsequrity.repository.RoleRepo;
import com.example.springsequrity.repository.UserRepo;
import com.example.springsequrity.util.JwtUtil;
import com.example.springsequrity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final RoleRepo rolesRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RegistrationRes registration(@Valid RegistrationRequest signupDto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setEmail(signupDto.getEmail());
        user.setFirstname(signupDto.getFirstname());
        user.setLastname(signupDto.getLastname());
        user.setRoles(rolesRepo.findRolesByName(signupDto.getRoles()));

        userRepo.save(user);
        return new RegistrationRes("User created successfully", true);
    }

    @Override
    public LoginRes login(LoginRequest loginRequest) {
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
        var loginResponse = new LoginRes(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public LoginRes refreshToken(RefreshTokenRequest refreshTokenRequest) {
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
            var loginResponse = new LoginRes(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new LoginRes();
    }

    @Override
    public User getCurrentlyLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
