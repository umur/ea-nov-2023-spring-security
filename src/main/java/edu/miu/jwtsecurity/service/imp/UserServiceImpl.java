package edu.miu.jwtsecurity.service.imp;

import edu.miu.jwtsecurity.dto.UserDto;
import edu.miu.jwtsecurity.model.Role;
import edu.miu.jwtsecurity.model.User;
import edu.miu.jwtsecurity.repository.RoleRepository;
import edu.miu.jwtsecurity.repository.UserRepository;
import edu.miu.jwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;


    @Override
    public UserDto findUserByEmail(String email) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(!byEmail.isPresent())
            throw new Exception("Email not exists");
        return modelMapper.map(byEmail.get(),UserDto.class);
    }

    @Override
    public User findUserDetailsByEmail(String email) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(!byEmail.isPresent())
            throw new Exception("Email not exists");
        return  byEmail.get();
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) throws Exception {
      //  userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent())
            throw new Exception("Email already exists");
        for (Role role : userDto.getRoles()) {
            roleRepository.save(role);
        }
        User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public User getUserFromSpringContext() throws Exception {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> byEmail = userRepository.findByEmail(userName);
        if(!byEmail.isPresent())
            throw new Exception("Email not exists");

        return byEmail.get();
    }

    @Override
    public List<UserDto> findAll() {
        List<User> all = userRepository.findAll();
        return all.stream().map( u -> modelMapper.map(u,UserDto.class)).collect(Collectors.toList());

    }
}
