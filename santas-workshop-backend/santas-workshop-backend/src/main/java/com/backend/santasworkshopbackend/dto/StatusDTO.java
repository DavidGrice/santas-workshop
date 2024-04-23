package com.backend.santasworkshopbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.backend.santasworkshopbackend.entity.Status;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;
    @JsonProperty("status_name")
    private String statusName;
    @JsonProperty("status_description")
    private String statusDescription;

    public StatusDTO(Status status, boolean shallow) {
        this.id = status.getId();
        this.statusName = status.getStatusName();
        this.statusDescription = status.getStatusDescription();
    }
    
}
