package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.UserDTO;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    Page<UserDTO> getUsers(Pageable pagedUsers);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(Long id);
    Page<UserDTO> searchUsers(String email, String userName, String password, String firstName, String lastName, Long roleID, Pageable pagedUsers);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
