package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;


@Entity
@Setter
@Getter
@Data
@Table(name = "deliveries")
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "c_name", nullable = false)
    private String cName;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "toy_id", nullable = false)
    private Toy toy;

    @Column(name = "status_type", nullable = false)
    private String statusType;

    @Column(name = "delivered_date", nullable = false)
    private Date deliveredDate;

    // getters and setters...
}