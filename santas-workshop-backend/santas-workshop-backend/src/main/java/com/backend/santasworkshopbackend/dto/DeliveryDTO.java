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
    private Long childID;
    @JsonProperty("location_id")
    private Long locationID;
    @JsonProperty("wishlist_id")
    private Long wishlistID;
    @JsonProperty("status_id")
    private Long statusID;
    @JsonProperty("delivered_date")
    private Date deliveredDate;

    public DeliveryDTO(Delivery delivery, boolean shallow) {
        this.id = delivery.getId();
        this.childID = delivery.getChildID().getId();
        this.locationID = delivery.getLocationID().getId();
        this.wishlistID = delivery.getWishlistID().getId();
        this.statusID = delivery.getStatusID().getId();
        this.deliveredDate = delivery.getDeliveredDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChildID() {
        return childID;
    }

    public void setChildID(Long childID) {
        this.childID = childID;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public Long getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(Long wishlistID) {
        this.wishlistID = wishlistID;
    }

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}