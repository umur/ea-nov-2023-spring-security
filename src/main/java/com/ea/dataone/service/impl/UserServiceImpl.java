package com.ea.dataone.service.impl;

import com.ea.dataone.dto.UserDto;
import com.ea.dataone.service.UserService;
import com.ea.dataone.entity.User;
import com.ea.dataone.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    public void create(UserDto user) {
        userRepo.save(modelMapper.map(user, User.class));
    }

    public List<UserDto> findAll() {
        return userRepo.findAll().stream()
                .map(user ->modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserDto user) {
        userRepo.save(modelMapper.map(user, User.class));
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if(user == null) return null;
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void delete(UserDto user) {
        userRepo.delete(modelMapper.map(user, User.class));
    }
}
