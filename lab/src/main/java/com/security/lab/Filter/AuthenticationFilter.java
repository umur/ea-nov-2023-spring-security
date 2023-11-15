package com.security.lab.Filter;

import com.security.lab.Entity.UserRole;
import com.security.lab.Utils.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private Jwt jwtUtil;

    public AuthenticationFilter(Jwt jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Check if the request path starts with "/uaa/"
            if (request.getRequestURI().startsWith("/uaa/")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = header.split(" ")[1].trim();

            if (!jwtUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtUtil.getUsernameFromToken(token);
            UserRole role = UserRole.valueOf(jwtUtil.getRoleFromToken(token));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
                    java.util.Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Modify the request to include the email as a parameter
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
                @Override
                public String getParameter(String name) {
                    if ("username".equals(name)) {
                        return username;
                    }
                    return super.getParameter(name);
                }
            };

            filterChain.doFilter(requestWrapper, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

}