package com.backend.santasworkshopbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.backend.santasworkshopbackend.dto.RoleDTO;
import com.backend.santasworkshopbackend.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/createRole")
    public ResponseEntity<RoleDTO> createRole(@Validated
                                              @RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/getRole/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Long id) {
        RoleDTO role = roleService.getRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Page<RoleDTO>> getAllRoles(Pageable pagedRoles) {
        Page <RoleDTO> role = roleService.getAllRoles(pagedRoles);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<RoleDTO> updateRole(@Validated
                                              @PathVariable("id") Long id,
                                              @RequestBody RoleDTO role) {

        role.setId(id);
        RoleDTO updatedRole = roleService.updateRole(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<Map<String, String>> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/roleExists/{name}")
    public ResponseEntity<Boolean> existsRoleByName(@PathVariable("name") String name) {
        boolean exists = roleService.existsRoleByName(name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/searchRoles")
    public ResponseEntity<Page<RoleDTO>> searchRoles(@RequestParam(required = false) Long id,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String description,
                                                     Pageable pagedRoles) {
        Page<RoleDTO> roles = roleService.searchRoles(id, name, description, pagedRoles);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    
}
