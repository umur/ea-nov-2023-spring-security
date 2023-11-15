package com.example.lab5.Service;

import com.example.lab5.Model.User;

public interface UserService {
    User findById(int id);
    User saveUser(User user);
   // User updateUser(int id,User user);
    void deleteUser(int id);

}
