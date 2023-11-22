package edu.miu.ea.security.service;

import edu.miu.ea.security.dto.UserDTO;
import edu.miu.ea.security.model.User;

import java.util.List;

public interface UserService {

    UserDTO findUserByEmail(String email) throws Exception;

    User findUserDetailsByEmail(String email) throws Exception;

    UserDTO addUser(UserDTO userDto) throws Exception;

    User getUserFromSpringContext() throws Exception;

    String getUserEmailFromSpringContext() throws Exception;

    List<UserDTO> findAll();

}
