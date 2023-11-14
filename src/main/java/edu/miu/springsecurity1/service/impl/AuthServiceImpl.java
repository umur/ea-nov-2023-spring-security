package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.User;
import edu.miu.springsecurity1.entity.dto.request.LoginRequest;
import edu.miu.springsecurity1.entity.dto.request.SignUpRequest;
import edu.miu.springsecurity1.entity.dto.response.LoginResponse;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.repository.RoleRepo;
import edu.miu.springsecurity1.repository.UserRepo;
import edu.miu.springsecurity1.util.JwtUtil;
import edu.miu.springsecurity1.service.AuthService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

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
    public LoginResponse login(LoginRequest loginRequest) {
        System.out.println("LOGIN REQUEST: " + loginRequest);
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
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
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
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new LoginResponse();
    }

    @Override
    public LoginResponse signup(SignUpRequest signUpRequest) {


        User user = new User();
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setRoles(rolesRepo.findByRoleIn(signUpRequest.getRoles()));

        userRepo.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(signUpRequest.getEmail());
        loginRequest.setPassword(signUpRequest.getPassword());
        return login(loginRequest);
    }

    @Override
    public String validate(SignUpRequest signUpRequest) {

        User user = userRepo.findByEmail(signUpRequest.getEmail());
        if (user != null) {
            return "User already exists";
        }

        // check if password is not empty
        if (signUpRequest.getPassword() == null || signUpRequest.getPassword().isEmpty()) {
            return "Password can not be empty";
        }

        if (signUpRequest.getFirstname() == null || signUpRequest.getFirstname().isEmpty()) {
            return "Firstname can not be empty";
        }

        if (signUpRequest.getLastname() == null || signUpRequest.getLastname().isEmpty()) {
            return "Lastname can not be empty";
        }

        // at least one role from the list {"CLIENT", "ADMIN"}
        if (signUpRequest.getRoles() == null || signUpRequest.getRoles().isEmpty()) {
            return "At least one role should be selected";
        }

        // check if the role is valid
        if (!signUpRequest.getRoles().contains("CLIENT") && !signUpRequest.getRoles().contains("ADMIN")) {
            return "Invalid roles";
        }


        return null;
    }

    @Override
    public User getCurrentUser() {
        return userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
