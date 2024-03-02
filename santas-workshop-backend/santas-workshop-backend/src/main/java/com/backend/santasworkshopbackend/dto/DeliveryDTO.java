package com.backend.santasworkshopbackend.dto;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DeliveryDTO {

    private Long id;
    private String cName;
    private Long locationId;
    private Long toyId;
    private String statusType;
    private Date deliveredDate;

    // Getters and setters
}