package edu.miu.ea.security.service.imp;

import edu.miu.ea.security.dto.UserDTO;
import edu.miu.ea.security.model.User;
import edu.miu.ea.security.repository.UserRepository;
import edu.miu.ea.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public UserDTO findUserByEmail(String email) throws Exception {
        Optional<User> byEmail = repository.findByEmail(email);
        if (!byEmail.isPresent())
            throw new Exception("Email not exists");
        return modelMapper.map(byEmail.get(), UserDTO.class);
    }

    @Override
    public User findUserDetailsByEmail(String email) throws Exception {
        Optional<User> byEmail = repository.findByEmail(email);
        if (!byEmail.isPresent())
            throw new Exception("Email not exists");
        return byEmail.get();
    }

    @Override
    @Transactional
    public UserDTO addUser(UserDTO userDto) throws Exception {
        //  userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Optional<User> byEmail = repository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent())
            throw new Exception("Email already exists");
        User savedUser = repository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public User getUserFromSpringContext() throws Exception {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> byEmail = repository.findByEmail(userName);
        if (!byEmail.isPresent())
            throw new Exception("Email not exists");

        return byEmail.get();
    }

    @Override
    public String getUserEmailFromSpringContext() throws Exception {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> all = repository.findAll();
        return all.stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());

    }

}
