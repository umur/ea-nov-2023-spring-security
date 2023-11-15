package edu.miu.jwtsecurity.dto;

import edu.miu.jwtsecurity.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UserDto {
    private Integer id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private List<Role> roles;
}
