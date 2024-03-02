package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Toy;

public interface ToyRepository extends JpaRepository<Toy, Long> {
}