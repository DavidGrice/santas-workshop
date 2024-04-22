package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.RoleDTO;
import com.backend.santasworkshopbackend.entity.Role;
import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.repository.ChildRepository;
import com.backend.santasworkshopbackend.repository.DeliveryRepository;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;
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

    private ChildRepository childRepository;
    private DeliveryRepository deliveryRepository;
    private DescriptionRepository descriptionRepository;
    private LocationRepository locationRepository;
    private RoleRepository roleRepository;
    private ToyRepository toyRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDescription(roleDTO.getRoleDescription());

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
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = new Role();

        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDescription(roleDTO.getRoleDescription());

        Role updatedRole = roleRepository.save(role);
        RoleDTO updatedRoleDTO = modelMapper.map(updatedRole, RoleDTO.class);
        return updatedRoleDTO;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    
}
