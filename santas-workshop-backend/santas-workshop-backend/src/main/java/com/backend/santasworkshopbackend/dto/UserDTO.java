package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.backend.santasworkshopbackend.entity.User;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password; // Consider not including this in the DTO for security reasons
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("role_id")
    private Long roleID;
    // Getters and setters
    public UserDTO(User user, boolean shallow) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        if (user.getRole() != null) {
            this.roleID = user.getRole().getId();
        } else {
            this.roleID = null;
        }
    }

    public Long getRoleID() {
        return roleID;
    }
    
    public void setRole(Long roleID) {
        this.roleID = roleID;
    }
}