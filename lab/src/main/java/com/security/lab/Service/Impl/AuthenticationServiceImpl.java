package com.security.lab.Service.Impl;

import com.security.lab.Dto.UserDto;
import com.security.lab.Entity.User;
import com.security.lab.Entity.UserRole;
import com.security.lab.Repository.UserRepo;
import com.security.lab.Service.AuthenticationService;
import com.security.lab.Utils.AuthenticationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private String SECRET_KEY = "MySecretKeyMySecretKeyMySecretKey";
    private SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");

    @Value("${jwt.expiration-time}")
    private long expirationTime; // Token expiration time in milliseconds
    private final UserRepo repository;
    @Override
    public String authenticate(AuthenticationRequest authenticationRequest) {
        Optional<User> optionalUser = repository.findUserByUsernameAndPassword(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return generateToken(user.getUsername(), user.getRole());
        }
        return "Invalid username or password";
    }

    @Override
    public void registerUser(UserDto userDto) {
        User user = userDTOToEntity(userDto);
        repository.save(user);
    }

    public String generateToken(String username, UserRole role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }
    public User userDTOToEntity(UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }
}
