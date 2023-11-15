package com.ea.dataone.controller;

import com.ea.dataone.dto.LoginRequest;
import com.ea.dataone.dto.LoginResponse;
import com.ea.dataone.dto.RefreshTokenRequest;
import com.ea.dataone.dto.RegisterRequest;
import com.ea.dataone.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/uaa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UaaController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}