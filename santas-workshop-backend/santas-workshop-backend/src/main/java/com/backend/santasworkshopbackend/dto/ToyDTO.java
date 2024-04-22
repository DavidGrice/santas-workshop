package com.backend.santasworkshopbackend.dto;

import java.sql.Date;

import com.backend.santasworkshopbackend.entity.Toy;

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
    private String name;
    private DescriptionDTO descriptionId; // Assuming Description entity has an id field
    private UserDTO addedById; // Assuming User entity has an id field
    private Date addedDate;
    private UserDTO updatedById; // Assuming User entity has an id field
    private Date updatedDate;
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