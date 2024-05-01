package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "child_statuses")
public class ChildStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "status_description", nullable = false)
    private String statusDescription;

    // getters and setters...
    public ChildStatus(ChildStatus status, boolean shallow) {
        if (status != null) {
            this.id = status.getId();
            this.statusName = status.getStatusName();
            this.statusDescription = status.getStatusDescription();
        }
    }
    
    public ChildStatus() {
    }
    
}
