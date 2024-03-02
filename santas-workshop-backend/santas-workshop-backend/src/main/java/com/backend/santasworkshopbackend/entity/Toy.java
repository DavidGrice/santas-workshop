package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Toy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @Column(name = "added_date", nullable = false)
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    // getters and setters...
}
