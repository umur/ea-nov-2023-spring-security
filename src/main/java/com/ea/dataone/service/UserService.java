package com.ea.dataone.service;

import com.ea.dataone.dto.UserDto;

import java.util.List;

public interface UserService {
    void create(UserDto user);

    List<UserDto> findAll();

    void update(UserDto user);

    UserDto getUser(Long id);

    void delete(UserDto user);
}
