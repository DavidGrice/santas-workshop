package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.backend.santasworkshopbackend.entity.User;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String userName;
    private String password; // Consider not including this in the DTO for security reasons
    private String firstName;
    private String lastName;
    private RoleDTO roleID; // Assuming RoleDTO has an id field

    // Getters and setters
    public UserDTO(User user, boolean shallow) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roleID = new RoleDTO(user.getRoleID(), true);
    }
}