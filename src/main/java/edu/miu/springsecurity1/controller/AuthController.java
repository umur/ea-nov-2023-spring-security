package edu.miu.springsecurity1.controller;

import edu.miu.springsecurity1.aspect.annotation.LogMe;
import edu.miu.springsecurity1.entity.dto.request.LoginRequest;
import edu.miu.springsecurity1.entity.dto.request.SignUpRequest;
import edu.miu.springsecurity1.entity.dto.response.LoginResponse;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.service.AuthService;
import lombok.extern.java.Log;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("auth controller: login");
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("auth controller: signup");

        String message = authService.validate(signUpRequest);

        if (message != null) {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        LoginResponse loginResponse = null;
        loginResponse = authService.signup(signUpRequest);

        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

}
