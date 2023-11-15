package com.example.backend1.util;

import com.example.backend1.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
//class to generate a Token
public class JwtTokenUtil {

    //    @Value("${app.jwt.secret}");
    @Value("${app.jwt.secret}")
    private String SECRET;

    //    @Value("${app.jwt.expire.duration}")
    @Value("${app.jwt.expire.duration}")
    private long EXPIRE_DURATION;

    //token is eventually a string
    //generate token
    //if our login is successful we will cal this method to generate a token
    public String generateToken(User user) {
        //Jwts is a inbuilt class in java to build token
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getEmail())//we should not use password here for security purpose
                .setIssuer("demo") //can be the issuer
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)//ES512 or HS512
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e){
            log.error(e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public Claims parseClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
    }


}