package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    
}
