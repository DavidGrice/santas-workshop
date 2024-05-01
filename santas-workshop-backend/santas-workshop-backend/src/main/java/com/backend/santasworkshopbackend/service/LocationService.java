package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.LocationDTO;

@Service
public interface LocationService {

    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocation(Long id);
    Page<LocationDTO> getAllLocations(Pageable pagedLocations);
    LocationDTO updateLocation(LocationDTO locationDTO);
    void deleteLocation(Long id);
    Page<LocationDTO> searchLocations(Long id, String address, String city, String stateProv, String zipCode, String country, String region, Double latitude, Double longitude, Pageable pagedLocations);
    boolean existsByAddress(String address);
    boolean existsByCity(String city);
    boolean existsByStateProv(String stateProv);
    boolean existsByZipCode(String zipCode);
    boolean existsByCountry(String country);
    boolean existsByRegion(String region);
    boolean existsByLatitudeAndLongitude(Double latitude, Double longitude);
}
