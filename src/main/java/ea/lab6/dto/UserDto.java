package ea.lab6.dto;

import ea.lab6.entity.Role;
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
