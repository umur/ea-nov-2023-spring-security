package ea.lab6.entity.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;

}
