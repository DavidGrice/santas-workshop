package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false)
    @JsonProperty("role_name")
    private String roleName;

    @Column(name = "role_description", nullable = false)
    @JsonProperty("role_description")
    private String roleDescription;

    // getters and setters...
    public Role(Role role, boolean shallow) {
        if (role != null) {
            this.id = role.getId();
            this.roleName = role.getRoleName();
            this.roleDescription = role.getRoleDescription();
        }
    }

    public Role() {
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}