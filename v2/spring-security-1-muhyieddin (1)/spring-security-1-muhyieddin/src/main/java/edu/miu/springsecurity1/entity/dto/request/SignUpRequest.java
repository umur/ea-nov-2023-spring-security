package edu.miu.springsecurity1.entity.dto.request;
import edu.miu.springsecurity1.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignUpRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private List<String> roles;
}
