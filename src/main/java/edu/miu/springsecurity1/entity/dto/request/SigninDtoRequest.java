package edu.miu.springsecurity1.entity.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninDtoRequest {
    private String email;
    private String password;
}
