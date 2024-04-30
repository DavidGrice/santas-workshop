package com.backend.santasworkshopbackend.dto;

import org.springframework.data.geo.Point;

import com.backend.santasworkshopbackend.entity.Location;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state_prov")
    private String stateProv;
    @JsonProperty("zip_code")
    private String zipCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("region")
    private String region;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;

    // Getters and setters
    public LocationDTO(Location location, boolean shallow) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.city = location.getCity();
        this.stateProv = location.getStateProv();
        this.zipCode = location.getZipCode();
        this.country = location.getCountry();
        this.region = location.getRegion();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}