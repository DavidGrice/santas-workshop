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
    private DescriptionDTO descriptionId; // Assuming Description entity has an id field
    @JsonProperty("added_by")
    private UserDTO addedById; // Assuming User entity has an id field
    @JsonProperty("added_date")
    private Date addedDate;
    @JsonProperty("updated_by")
    private UserDTO updatedById; // Assuming User entity has an id field
    @JsonProperty("updated_date")
    private Date updatedDate;
    @JsonProperty("quantity")
    private Long quantity;

    // Getters and setters
    public ToyDTO(Toy toy, boolean shallow) {
        this.id = toy.getId();
        this.name = toy.getName();
        this.descriptionId = new DescriptionDTO(toy.getDescription(), true);
        this.addedById = new UserDTO(toy.getAddedBy(), true);
        this.addedDate = toy.getAddedDate();
        this.updatedById = new UserDTO(toy.getUpdatedBy(), true);
        this.updatedDate = toy.getUpdatedDate();
        this.quantity = toy.getQuantity();
    }
}