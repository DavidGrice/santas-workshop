package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Toy;

public interface ToyRepo extends JpaRepository<Toy, Long> {
}