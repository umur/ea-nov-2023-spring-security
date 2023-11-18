package edu.miu.springsecurity1.controller;

import edu.miu.springsecurity1.entity.dto.request.SigninDtoRequest;
import edu.miu.springsecurity1.entity.dto.request.SignupDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.SigninDtoResponse;
import edu.miu.springsecurity1.entity.dto.request.RefreshTokenRequest;
import edu.miu.springsecurity1.service.UaaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UaaController {

    private final UaaService uaaService;

    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDtoRequest signinDtoRequest) {
        var loginResponse = uaaService.signin(signinDtoRequest);
        return new ResponseEntity<SigninDtoResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDtoRequest signupDtoRequest) {
        var signupDtoResponse = uaaService.signup(signupDtoRequest);
        return new ResponseEntity<>(
                signupDtoResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public SigninDtoResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return uaaService.refreshToken(refreshTokenRequest);
    }

}
