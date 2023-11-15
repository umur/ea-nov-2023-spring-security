package com.example.lab5.Controller;

import com.example.lab5.Model.User;
import com.example.lab5.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id)
    {
        return userService.findById(id);
    }
    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
  /*  @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable int id){
        return userService.updateUser(id,user);
    }*/
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

}
