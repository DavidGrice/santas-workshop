package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;
import org.springframework.data.geo.Point;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state_prov", nullable = false)
    private String stateProv;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "coordinates", nullable = false)
    private Point coordinates; // This should be a Point type if your ORM supports it

    // getters and setters...
}
