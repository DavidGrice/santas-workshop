package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {
    boolean existsByAddress(String address);
    boolean existsByCity(String city);
    boolean existsByStateProv(String stateProv);
    boolean existsByZipCode(String zipCode);
    boolean existsByCountry(String country);
    boolean existsByRegion(String region);
    boolean existsByLatitudeAndLongitude(Double latitude, Double longitude);
}
