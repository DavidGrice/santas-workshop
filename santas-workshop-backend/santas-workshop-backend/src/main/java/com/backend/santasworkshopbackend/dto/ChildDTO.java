package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import com.backend.santasworkshopbackend.entity.Child;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildDTO {

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("status_id")
    private Long statusID;
    @JsonProperty("location_id")
    private Long locationID;

    public ChildDTO(Child child, boolean shallow) {
        this.id = child.getId();
        this.firstName = child.getFirstName();
        this.lastName = child.getLastName();
        this.birthdate = child.getBirthdate();
        this.statusID = child.getStatusID().getId();
        this.locationID = child.getLocationID().getId();
    }
}
