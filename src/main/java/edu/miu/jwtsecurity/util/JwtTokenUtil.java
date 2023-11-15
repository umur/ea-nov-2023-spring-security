package edu.miu.jwtsecurity.util;

import edu.miu.jwtsecurity.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {


    @Value("${app.jwt.secret}")
    private String SECRET;

    @Value("${app.jwt.expire.duration}")
    private long EXPIRE_DURATION;

    public String  generateToken(User user )
    {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .claim("roles",user.getRolesName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }

    public Boolean validateToken(String token)
    {

        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        }  catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
