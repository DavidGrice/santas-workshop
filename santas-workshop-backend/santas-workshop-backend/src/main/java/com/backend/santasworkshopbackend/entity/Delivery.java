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

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child childID;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location locationId;

    @ManyToOne
    @JoinColumn(name = "toy_id", nullable = false)
    private Toy toyId;

    @Column(name = "status_type", nullable = false)
    private String statusType;

    @Column(name = "delivered_date", nullable = false)
    private Date deliveredDate;

    // getters and setters...
    public Delivery(Delivery delivery, boolean shallow) {
        if (delivery != null) {
            this.id = delivery.getId();
            this.childID = delivery.getChildID();
            this.locationId = delivery.getLocationId();
            this.toyId = delivery.getToyId();
            this.statusType = delivery.getStatusType();
            this.deliveredDate = delivery.getDeliveredDate();
        }
    }

    public Delivery() {
    }
}