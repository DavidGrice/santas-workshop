package com.backend.santasworkshopbackend.dto;
import java.sql.Date;

import com.backend.santasworkshopbackend.entity.Delivery;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private Long id;
    @JsonProperty("child_id")
    private ChildDTO childID;
    @JsonProperty("location_id")
    private LocationDTO locationID;
    @JsonProperty("toy_id")
    private ToyDTO toyID;
    @JsonProperty("status_id")
    private StatusDTO statusID;
    @JsonProperty("delivered_date")
    private Date deliveredDate;

    // Getters and setters
    public DeliveryDTO(Delivery delivery, boolean shallow) {
        this.id = delivery.getId();
        this.childID = new ChildDTO(delivery.getChildID(), true);
        this.locationID = new LocationDTO(delivery.getLocationID(), true);
        this.toyID = new ToyDTO(delivery.getToyID(), true);
        this.statusID = new StatusDTO(delivery.getStatusID(), true);
        this.deliveredDate = delivery.getDeliveredDate();
    }
}