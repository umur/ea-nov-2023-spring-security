package ea.lab6.controller;


import ea.lab6.dto.UserDto;
import ea.lab6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
@GetMapping
    public List<UserDto> findAll()
{
    return  userService.findAll();
}

}
