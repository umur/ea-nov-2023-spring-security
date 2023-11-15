package com.security.lab.Dto;

import com.security.lab.Entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private UserRole role;
}
