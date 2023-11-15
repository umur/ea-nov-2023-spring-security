package com.example.springsequrity.service.impl;

import com.example.springsequrity.repository.UserRepo;
import com.example.springsequrity.service.impl.AwesomeUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class AwesomeUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public AwesomeUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmail(username);
        var userDetails = new AwesomeUserDetails(user);
        return userDetails;
    }

}
