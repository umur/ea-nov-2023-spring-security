package com.security.lab.Service;

import com.security.lab.Dto.UserDto;
import com.security.lab.Utils.AuthenticationRequest;

public interface AuthenticationService {
    String authenticate(AuthenticationRequest authenticationRequest);

    void registerUser(UserDto userDto);
}
