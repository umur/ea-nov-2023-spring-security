package com.example.backend1.service;


import com.example.backend1.exceptions.ResourceNotFoundException;
import com.example.backend1.exceptions.UserAlreadyExistsException;
import com.example.backend1.exceptions.UserNotFoundException;
import com.example.backend1.model.Product;
import com.example.backend1.model.User;
import com.example.backend1.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend1.payloads.UserDto;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;


    //Add user
    @Override
    public User save(User user) {
        if(userAlreadyExists(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }

        //encode the password and then only save to the repository
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    //Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Get Specific Used BY ID
    @Override
    public User getUserById(int id) {

        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Sorry, this user could not be found"));
    }

    //Delete User BY ID
    @Override
    public void deleteUserById(int id) {

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("Sorry, User not Found");
        }

        this.userRepository.deleteById(id);

    }

    @Override
    public User updateUserById(int id, User user) {
        return userRepository.findById(id).map(u ->{
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setSsn(user.getSsn());
            u.setRoles(user.getRoles());

            return userRepository.save(u);
        }).orElseThrow(()-> new UserNotFoundException("Sorry, this user could not be found"));
    }


    private boolean userAlreadyExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }


}

