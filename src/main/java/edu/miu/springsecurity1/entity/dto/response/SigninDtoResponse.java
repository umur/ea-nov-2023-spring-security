package edu.miu.springsecurity1.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDtoResponse {
    private String accessToken;
    private String refreshToken;
}
