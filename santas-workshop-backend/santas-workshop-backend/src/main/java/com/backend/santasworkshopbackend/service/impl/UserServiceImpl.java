package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.UserDTO;
import com.backend.santasworkshopbackend.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setDelivery(userDTO.getDelivery());
        user.setLocation(userDTO.getLocation());
        user.setDescription(userDTO.getDescription());
        userRepository.save(user);
        return userDTO;
    };
    
}
