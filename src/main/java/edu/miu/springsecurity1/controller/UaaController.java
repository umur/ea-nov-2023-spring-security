package edu.miu.springsecurity1.controller;


import edu.miu.springsecurity1.entity.dto.request.LoginRequest;
import edu.miu.springsecurity1.entity.dto.request.SignupDto;
import edu.miu.springsecurity1.entity.dto.response.LoginResponse;
import edu.miu.springsecurity1.entity.dto.response.SignupResponse;
import edu.miu.springsecurity1.service.AuthService;
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
        return new ResponseEntity<LoginResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupDto signupDto) {
        var signupResponse = authService.signup(signupDto);
        return new ResponseEntity<SignupResponse>(
                signupResponse, HttpStatus.OK);
    }
}
