package com.backend.santasworkshopbackend.dto;
import java.sql.Date;

import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.entity.Delivery;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private Long id;
    private ChildDTO childID;
    private LocationDTO locationId;
    private ToyDTO toyId;
    private String statusType;
    private Date deliveredDate;

    // Getters and setters
    public DeliveryDTO(Delivery delivery, boolean shallow) {
        this.id = delivery.getId();
        this.childID = new ChildDTO(delivery.getChildID(), true);
        this.locationId = new LocationDTO(delivery.getLocationId(), true);
        this.toyId = new ToyDTO(delivery.getToyId(), true);
        this.statusType = delivery.getStatusType();
        this.deliveredDate = delivery.getDeliveredDate();
    }
}