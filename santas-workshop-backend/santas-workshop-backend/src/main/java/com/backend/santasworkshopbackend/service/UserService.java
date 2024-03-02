package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    Page<UserDTO> getUsers(Pageable pageable);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    
}
