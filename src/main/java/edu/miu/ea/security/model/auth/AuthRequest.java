package edu.miu.ea.security.model.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;

}
