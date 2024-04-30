package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.backend.santasworkshopbackend.entity.Wishlist;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {

    private Long id;
    @JsonProperty("child_id")
    private Long childID;
    @JsonProperty("toy_id")
    private Long toyID;

    public WishlistDTO(Wishlist wishlist, boolean shallow) {
        this.id = wishlist.getId();
        this.childID = wishlist.getChild().getId();
        this.toyID = wishlist.getToy().getId();
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

    public Long getToyID() {
        return toyID;
    }

    public void setToyID(Long toyID) {
        this.toyID = toyID;
    }
    
}
