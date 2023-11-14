package com.example.lab5.Service.Impl;

import com.example.lab5.Model.User;
import com.example.lab5.Service.UserService;

import com.example.lab5.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User findById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

   /* @Override
    public User updateUser(int id, User user) {
    return userRepo.updateById(id,user);
    }
*/
    @Override
    public void deleteUser(int id) {
    userRepo.deleteById(id);
    }


}
