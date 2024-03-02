package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
