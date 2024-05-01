package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long>, JpaSpecificationExecutor<Description> {
    boolean existsByName(String name);
    boolean existsByDescription(String description);
}
