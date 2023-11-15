package com.example.lab5.Util;

import com.example.lab5.Model.User;
import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
@Slf4j
@Component
public class JwtTokenUtil {
    @Value("123456789123456789")
    private String SECRET;
    @Value("900000")
    private Long EXPIRE_DURATION;

    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getId() + "," + user.getEmail()).setIssuer("miu")
                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {

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