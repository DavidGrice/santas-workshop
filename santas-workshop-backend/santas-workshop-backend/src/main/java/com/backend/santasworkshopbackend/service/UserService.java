package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.UserDTO;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    Page<UserDTO> getUsers(Pageable pageable);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    
}
