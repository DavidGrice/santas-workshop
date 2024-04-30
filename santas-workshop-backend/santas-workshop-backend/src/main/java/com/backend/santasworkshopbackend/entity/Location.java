package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    private String address;

    @Column(name = "city", nullable = false)
    @JsonProperty("city")
    private String city;

    @Column(name = "state_prov", nullable = false)
    @JsonProperty("state_prov")
    private String stateProv;

    @Column(name = "zip_code", nullable = false)
    @JsonProperty("zip_code")
    private String zipCode;

    @Column(name = "country", nullable = false)
    @JsonProperty("country")
    private String country;

    @Column(name = "region", nullable = false)
    @JsonProperty("region")
    private String region;
    
    @Column(name = "latitude", nullable = false)
    @JsonProperty("latitude")
    private Double latitude;
    
    @Column(name = "longitude", nullable = false)
    @JsonProperty("longitude")
    private Double longitude;

    // getters and setters...
    public Location(Location location, boolean shallow) {
        if (location != null) {
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

    public Location() {
    }
}
