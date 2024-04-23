package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.backend.santasworkshopbackend.entity.Role;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("role_description")
    private String roleDescription; // Assuming User entity has an id field

    // Getters and setters
    public RoleDTO(Role role, boolean shallow) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.roleDescription = role.getRoleDescription();
    }
}