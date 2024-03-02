package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleDTO {

    private Long id;
    private String roleName;
    private Long userId; // Assuming User entity has an id field

    // Getters and setters
}