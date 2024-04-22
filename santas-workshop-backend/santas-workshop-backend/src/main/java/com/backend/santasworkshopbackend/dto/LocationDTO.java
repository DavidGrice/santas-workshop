package com.backend.santasworkshopbackend.dto;

import org.springframework.data.geo.Point;

import com.backend.santasworkshopbackend.entity.Location;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;
    private String address;
    private String city;
    private String stateProv;
    private String country;
    private String region;
    private Point coordinates; // This should be a Point type if your ORM supports it

    // Getters and setters
    public LocationDTO(Location location, boolean shallow) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.city = location.getCity();
        this.stateProv = location.getStateProv();
        this.country = location.getCountry();
        this.region = location.getRegion();
        this.coordinates = location.getCoordinates();
    }
}