package com.backend.santasworkshopbackend.dto;

import org.springframework.data.geo.Point;
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
public class LocationDTO {

    private Long id;
    private String address;
    private String city;
    private String stateProv;
    private String country;
    private String region;
    private Point coordinates; // This should be a Point type if your ORM supports it

    // Getters and setters
}