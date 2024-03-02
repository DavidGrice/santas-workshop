package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.UserDTO;
import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.backend.santasworkshopbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        @SuppressWarnings("null")
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long id) {
        @SuppressWarnings("null")
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @SuppressWarnings("null")
    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        @SuppressWarnings("null")
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}
