package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.backend.santasworkshopbackend.entity.Description;

import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionDTO {

    private Long id;
    private String name;
    private String description;

    // Getters and setters
    public DescriptionDTO(Description description, boolean shallow) {
        this.id = description.getId();
        this.name = description.getName();
        this.description = description.getDescription();
    }
    
}