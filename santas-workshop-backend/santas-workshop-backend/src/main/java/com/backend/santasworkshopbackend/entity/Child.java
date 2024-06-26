package com.backend.santasworkshopbackend.entity;

import java.sql.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ChildStatus statusID;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location locationID;

    // getters and setters...
    public Child(Child child, boolean shallow) {
        if (child != null) {
            this.id = child.getId();
            this.firstName = child.getFirstName();
            this.lastName = child.getLastName();
            this.birthdate = child.getBirthdate();
            this.statusID = child.getStatusID();
            this.locationID = child.getLocationID();
        }
    }
    
    public Child() {
    }
}
