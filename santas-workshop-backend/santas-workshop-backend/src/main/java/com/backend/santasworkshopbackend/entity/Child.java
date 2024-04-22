package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;

import com.backend.santasworkshopbackend.dto.ChildDTO;
import com.backend.santasworkshopbackend.dto.LocationDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Id
    @Column(name = "status_type", nullable = false)
    private String statusType;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // getters and setters...
    public Child(Child child, boolean shallow) {
        if (child != null) {
            this.id = child.getId();
            this.firstName = child.getFirstName();
            this.lastName = child.getLastName();
            this.age = child.getAge();
            this.statusType = child.getStatusType();
            this.location = child.getLocation();
        }
    }
    
    public Child() {
    }
}
