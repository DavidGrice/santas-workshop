package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.backend.santasworkshopbackend.entity.Child;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String statusType;
    private LocationDTO childLocation;
    private LocationDTO childCoordinates;

    public ChildDTO(Child child, boolean shallow) {
        this.id = child.getId();
        this.firstName = child.getFirstName();
        this.lastName = child.getLastName();
        this.age = child.getAge();
        this.statusType = child.getStatusType();
        this.childLocation = new LocationDTO(child.getLocation(), true);
    }
}
