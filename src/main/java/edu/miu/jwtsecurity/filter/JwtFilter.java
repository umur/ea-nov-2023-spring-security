package edu.miu.jwtsecurity.filter;

import edu.miu.jwtsecurity.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.split(" ")[1];
        boolean result = jwtUtil.validateToken(token);
        if(!result){
            filterChain.doFilter(request, response);
            return;
        }


        Claims claims = jwtUtil.parseClaims(token);
        String subject = claims.getSubject(); //1234,test@miu.edu
        List<String>  roles = (List<String>) claims.get("roles");
        System.out.println(roles);
        String[] info = subject.split(",");
        String email = info[0];
        List<SimpleGrantedAuthority> rols = roles.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,rols );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }


}
