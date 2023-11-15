package edu.miu.jwtsecurity.controller;


import edu.miu.jwtsecurity.dto.UserDto;
import edu.miu.jwtsecurity.model.User;
import edu.miu.jwtsecurity.model.auth.AuthRequest;
import edu.miu.jwtsecurity.model.auth.AuthResponse;
import edu.miu.jwtsecurity.service.UserService;
import edu.miu.jwtsecurity.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {

        try {
            System.out.println(authRequest.getEmail());
            System.out.println(authRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User u = (User) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(u);
            return ResponseEntity.ok(new AuthResponse(u.getEmail(), token));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


    @PostMapping("/signup")
    public UserDto signup(@RequestBody UserDto userDto) throws Exception {

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserDto savedUser = userService.addUser(userDto);
        savedUser.setPassword(null);
        return  savedUser;

    }

}
