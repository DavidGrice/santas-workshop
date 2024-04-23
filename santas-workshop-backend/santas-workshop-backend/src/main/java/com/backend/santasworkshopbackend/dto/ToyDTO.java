package com.backend.santasworkshopbackend.dto;

import java.sql.Date;

import com.backend.santasworkshopbackend.entity.Toy;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToyDTO {

    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description_id")
    private Long descriptionID; // Assuming Description entity has an id field
    @JsonProperty("added_by")
    private Long addedById; // Assuming User entity has an id field
    @JsonProperty("added_date")
    private Date addedDate;
    @JsonProperty("updated_by")
    private Long updatedById; // Assuming User entity has an id field
    @JsonProperty("updated_date")
    private Date updatedDate;
    @JsonProperty("quantity")
    private Long quantity;

    // Getters and setters
    public ToyDTO(Toy toy, boolean shallow) {
        this.id = toy.getId();
        this.name = toy.getName();
        this.descriptionID = toy.getDescription().getId();
        this.addedById = toy.getAddedBy().getId();
        this.addedDate = toy.getAddedDate();
        this.updatedById = toy.getUpdatedBy().getId();
        this.updatedDate = toy.getUpdatedDate();
        this.quantity = toy.getQuantity();
    }
}