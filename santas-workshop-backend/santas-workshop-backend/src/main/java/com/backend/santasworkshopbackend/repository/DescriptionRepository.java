package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
