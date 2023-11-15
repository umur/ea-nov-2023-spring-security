package com.example.backend1.payloads;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


import java.util.List;

@Data

//@Service
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be greater than 4")
    private String name;

    @Valid
    @Email
    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;

    @NotEmpty(message = "password is mandatory")
    @Size(min = 3, max = 10, message = "password must be min 3 chars and max 10chars")
//    @Pattern(regexp = "")
    private String password;

    @NotNull
    private String ssn;

    private List<String> roles;

}

