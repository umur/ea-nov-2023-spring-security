package edu.miu.ea.security.dto;

import edu.miu.ea.security.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private List<Role> roles;
}
