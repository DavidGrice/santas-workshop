package com.backend.santasworkshopbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    // Page<Wishlist> findByChildId(Long childId);
    // Page<Wishlist> findByToyId(Long toyId);
    // Page<Wishlist> findByChildIdAndToyId(Long childId, Long toyId);
    
} 
