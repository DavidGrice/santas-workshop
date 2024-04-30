package com.backend.santasworkshopbackend.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne
    @JoinColumn(name = "toy_id", nullable = false)
    private Toy toy;

    // getters and setters...
    public Wishlist(Wishlist wishlist, boolean shallow) {
        if (wishlist != null) {
            this.id = wishlist.getId();
            this.child = wishlist.getChild();
            this.toy = wishlist.getToy();
        }
    }

    public Wishlist() {
    }
    
}
