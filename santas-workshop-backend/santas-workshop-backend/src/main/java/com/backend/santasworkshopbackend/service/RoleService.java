package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.RoleDTO;

@Service

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRole(Long id);
    Page<RoleDTO> getAllRoles();
    RoleDTO updateRole(RoleDTO roleDTO);
    void deleteRole(Long id);
    
}
