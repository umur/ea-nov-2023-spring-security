package edu.miu.springsecurity1.entity.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupDtoRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int roleId;
}
