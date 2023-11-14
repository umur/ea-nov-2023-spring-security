package edu.miu.springsecurity1.service.impl;

import edu.miu.springsecurity1.entity.User;
import edu.miu.springsecurity1.repository.UserRepo;
import edu.miu.springsecurity1.service.impl.AwesomeUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

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

    //check if the user exists in the database
    //if yes, return the user
    //if no, throw HttpStatusException


}
