package com.backend.santasworkshopbackend.dto;

import java.sql.Date;
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
public class ToyDTO {

    private Long id;
    private String name;
    private Long descriptionId; // Assuming Description entity has an id field
    private Long addedById; // Assuming User entity has an id field
    private Date addedDate;
    private Long updatedById; // Assuming User entity has an id field
    private Date updatedDate;
    private Long quantity;

    // Getters and setters
}