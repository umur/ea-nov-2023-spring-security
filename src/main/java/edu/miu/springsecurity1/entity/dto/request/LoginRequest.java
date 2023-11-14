package edu.miu.springsecurity1.entity.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequest {
    private String email;
    private String password;


}
