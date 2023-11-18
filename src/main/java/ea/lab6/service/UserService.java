package ea.lab6.service;

import ea.lab6.dto.UserDto;
import ea.lab6.entity.User;

import java.util.List;

public interface UserService {

      UserDto findUserByEmail(String email) throws Exception;
    User findUserDetailsByEmail(String email) throws Exception;
    UserDto addUser(UserDto userDto) throws Exception;

    User getUserFromSpringContext() throws Exception;
    String getUserEmailFromSpringContext() throws Exception;
    List<UserDto> findAll();

}
