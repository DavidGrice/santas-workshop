package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "status_description", nullable = false)
    private String statusDescription;

    // getters and setters...
    public Status(Status status, boolean shallow) {
        if (status != null) {
            this.id = status.getId();
            this.statusName = status.getStatusName();
            this.statusDescription = status.getStatusDescription();
        }
    }
    
    public Status() {
    }
    
}
