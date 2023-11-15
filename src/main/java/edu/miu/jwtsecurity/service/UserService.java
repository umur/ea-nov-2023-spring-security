package edu.miu.jwtsecurity.service;

import edu.miu.jwtsecurity.dto.UserDto;
import edu.miu.jwtsecurity.model.User;

import java.util.List;

public interface UserService {

      UserDto findUserByEmail(String email) throws Exception;
    User findUserDetailsByEmail(String email) throws Exception;
    UserDto addUser(UserDto userDto) throws Exception;

    User getUserFromSpringContext() throws Exception;
    String getUserEmailFromSpringContext() throws Exception;
    List<UserDto> findAll();

}
