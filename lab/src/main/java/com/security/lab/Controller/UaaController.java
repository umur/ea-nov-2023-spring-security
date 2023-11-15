package com.security.lab.Controller;

import com.security.lab.Dto.UserDto;
import com.security.lab.Service.AuthenticationService;
import com.security.lab.Utils.AuthenticationRequest;
import com.security.lab.Utils.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/uaa")
public class UaaController {
    private final AuthenticationService authenticationService;

    @GetMapping("")
    public ResponseEntity get(){
        return ResponseEntity.ok(123);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        authenticationService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        String token = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}

