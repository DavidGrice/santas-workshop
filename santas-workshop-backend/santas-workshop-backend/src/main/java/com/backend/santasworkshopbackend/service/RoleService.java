package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.RoleDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRole(Long id);
    List<RoleDTO> getAllRoles();
    RoleDTO updateRole(RoleDTO roleDTO);
    void deleteRole(Long id);
    
}
