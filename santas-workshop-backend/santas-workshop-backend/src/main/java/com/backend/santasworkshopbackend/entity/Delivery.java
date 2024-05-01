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
    private Location locationID;

    @ManyToOne
    @JoinColumn(name = "toy_id", nullable = false)
    private Toy toyID;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private DeliveryStatus statusID;

    @Column(name = "delivered_date", nullable = false)
    private Date deliveredDate;

    // getters and setters...
    public Delivery(Delivery delivery, boolean shallow) {
        if (delivery != null) {
            this.id = delivery.getId();
            this.childID = delivery.getChildID();
            this.locationID = delivery.getLocationID();
            this.toyID = delivery.getToyID();
            this.statusID = delivery.getStatusID();
            this.deliveredDate = delivery.getDeliveredDate();
        }
    }

    public Delivery() {
    }
}