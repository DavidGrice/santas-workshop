package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import com.backend.santasworkshopbackend.entity.Description;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionDTO {

    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;

    // Getters and setters
    public DescriptionDTO(Description description, boolean shallow) {
        this.id = description.getId();
        this.name = description.getName();
        this.description = description.getDescription();
    }
    
}