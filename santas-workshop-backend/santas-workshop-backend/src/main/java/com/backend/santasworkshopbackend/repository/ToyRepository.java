package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Toy;

public interface ToyRepository extends JpaRepository<Toy, Long>, JpaSpecificationExecutor<Toy> {
    boolean existsByName(String name);
}