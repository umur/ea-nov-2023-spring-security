package com.example.backend1.service;

import com.example.backend1.model.Product;
import com.example.backend1.model.User;
import com.example.backend1.payloads.UserDto;

import java.util.List;


public interface UserService {

    //create User
    User save(User u);

    //get all Users
    List<User> getAllUsers();

    //get single user
    User getUserById(int id);

    //delete user by Id
    void deleteUserById(int id);

    //update user by id
    User updateUserById(int id, User user);



}
