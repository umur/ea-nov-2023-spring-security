package com.example.springsequrity.controller;


import com.example.springsequrity.entity.dto.request.LoginRequest;
import com.example.springsequrity.entity.dto.request.RegistrationRequest;
import com.example.springsequrity.entity.dto.response.LoginRes;
import com.example.springsequrity.entity.dto.response.RegistrationRes;
import com.example.springsequrity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UaaController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> singin(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginRes>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationRequest registration) {
        var signupResponse = authService.registration(registration);
        return new ResponseEntity<RegistrationRes>(
                signupResponse, HttpStatus.OK);
    }
}
