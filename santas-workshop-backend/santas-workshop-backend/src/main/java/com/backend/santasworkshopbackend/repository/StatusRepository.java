package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    
}
