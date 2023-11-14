package com.ea.dataone.controller;

import com.ea.dataone.dto.UserDto;
import com.ea.dataone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody UserDto user) {
        userService.create(user);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping
    public void update(@RequestBody UserDto user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        userService.delete(user);
    }
}