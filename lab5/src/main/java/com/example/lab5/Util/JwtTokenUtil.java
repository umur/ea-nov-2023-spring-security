package com.example.lab5.Util;

import com.example.lab5.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtTokenUtil {
    @Value("${app.jwt.secret}")
    private String SECRET;
    @Value("${app.jwt.expire.duration}")
    private Long EXPIRE_DURATION;
     public String generateToken(User user){
       return Jwts.builder().setSubject(user.getId()+","+user.getEmail()).setIssuer("miu")
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                         .signWith(SignatureAlgorithm.ES512,SECRET)
                 .compact();
     }
}
