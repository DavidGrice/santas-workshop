package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.UserDTO;
import com.backend.santasworkshopbackend.entity.Role;
import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.backend.santasworkshopbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // Logger.info(userDTO.getEmail() + " " + userDTO.getUserName() + " " + userDTO.getPassword() + " " + userDTO.getFirstName() + " " + userDTO.getLastName() + " " + userDTO.getRoleID());
        // Role role = roleRepository.findById(userDTO.getRoleID())
        //     .orElseThrow(() -> new RuntimeException("Role not found"));
        // User user = modelMapper.map(userDTO, User.class);
        // user.setRole(role);
        // Logger.info(user.getEmail() + " " + user.getUserName() + " " + user.getPassword() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getRole());
        // User savedUser = userRepository.save(user);

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
    
        Role role = roleRepository.findById(userDTO.getRoleID())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
    
        User createdUser = userRepository.save(user);
        UserDTO savedUserDTO = modelMapper.map(createdUser, UserDTO.class);
        return savedUserDTO;
    }

    @Override
    public UserDTO getUser(Long id) {
        User user;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pagedUsers) {
        // List<User> users = userRepository.findAll();
        // return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return userRepository.findAll(pagedUsers).map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setEmail(user.getEmail());
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        try {
            Role role = roleRepository.findById(user.getRoleID()).orElseThrow(() -> new RoleNotFoundException("Role not found"));
            existingUser.setRole(role);
        } catch (RoleNotFoundException e) {
            throw new RuntimeException("Role not found", e);
        }
        
        User updateduser = userRepository.save(existingUser);
        return modelMapper.map(updateduser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}
