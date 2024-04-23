package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.RoleDTO;
import com.backend.santasworkshopbackend.entity.Role;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.service.RoleService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        Role savedRole = roleRepository.save(role);
        RoleDTO createdRole = modelMapper.map(savedRole, RoleDTO.class);
        return createdRole;
    }

    @Override
    public RoleDTO getRole(Long id) {
        Role role;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            role = optionalRole.get();
        } else {
            return null;
        }    

        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        return roleDTO;
    }

    @Override
    public Page<RoleDTO> getAllRoles(Pageable pagedRoles) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());

        return roleRepository.findAll(pagedRoles).map(role -> modelMapper.map(role, RoleDTO.class));
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) {
        Role exisitingRole = roleRepository.findById(role.getId())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        exisitingRole.setRoleName(role.getRoleName());
        exisitingRole.setRoleDescription(role.getRoleDescription());
        Role updatedRole = roleRepository.save(exisitingRole);
        return modelMapper.map(updatedRole, RoleDTO.class);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    
}
