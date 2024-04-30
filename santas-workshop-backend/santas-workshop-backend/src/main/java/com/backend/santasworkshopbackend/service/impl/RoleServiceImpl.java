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
        Logger.info("Role created createRole(RoleDTO roleDTO)" + createdRole);
        return createdRole;
    }

    @Override
    public RoleDTO getRole(Long id) {
        Role role;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            role = optionalRole.get();
            RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
            Logger.info("Role exists in getRole(Long id)" + roleDTO);
            return roleDTO;
        } else {
            Logger.error("Error in getRole(Long id)");
            return null;
        }
    }

    @Override
    public Page<RoleDTO> getAllRoles(Pageable pagedRoles) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());
        
        Logger.info("Role exists in getAllRoles(Pageable pagedRoles)" + pagedRoles);
        return roleRepository.findAll(pagedRoles).map(role -> modelMapper.map(role, RoleDTO.class));
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) {

        Role exisitingRole = roleRepository.findById(role.getId())
            .orElseThrow(() -> new RuntimeException("Description not found"));
        exisitingRole.setRoleName(role.getRoleName());
        exisitingRole.setRoleDescription(role.getRoleDescription());
        Role updatedRole = roleRepository.save(exisitingRole);
        Logger.info("Role exists in updateRole(RoleDTO role)" + role);
        return modelMapper.map(updatedRole, RoleDTO.class);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
        Logger.info("Role deleted deleteRole(Long id)" + id);
    }

    
    @Override
    public RoleDTO getRoleByName(String name) {
        Role role = roleRepository.findByRoleName(name);
        Logger.info("Role exists in getRoleByName(String name)" + role);
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        return roleDTO;
    }

    @Override
    public boolean existsRoleByName(String name) {
        Logger.info("Role exists in existsRoleByName(String name)" + name);
        return roleRepository.existsByRoleName(name);
    }
    
}
