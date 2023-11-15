package com.example.backend1.controller;

import com.example.backend1.exceptions.ApiResponse;
import com.example.backend1.model.User;
import com.example.backend1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend1.payloads.UserDto;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //save Users
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User u){
        User user = userService.save(u);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    //Get User By Id
    @GetMapping("/{id}")
    public User getSpecificUser(@PathVariable int id){
        return this.userService.getUserById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        User updatedUser = userService.updateUserById(id, user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }


}

