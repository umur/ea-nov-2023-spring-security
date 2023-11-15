package com.security.lab.Config;

import com.security.lab.Entity.UserRole;
import com.security.lab.Filter.AuthenticationFilter;
import com.security.lab.Utils.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private Jwt jwtUtil = new Jwt();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtUtil);

        return http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((matcher) -> matcher
                                .requestMatchers("/uaa/signin", "/uaa/signup").permitAll()
                                .requestMatchers("/uaa/**").authenticated()
                        .requestMatchers("/api/products").hasAnyRole(UserRole.USER.toString())
                        .requestMatchers("/api/products/**").hasAnyRole(UserRole.USER.toString())
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
