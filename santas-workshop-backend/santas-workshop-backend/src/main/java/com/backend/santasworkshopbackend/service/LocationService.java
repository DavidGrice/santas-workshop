package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.LocationDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocation(Long id);
    List<LocationDTO> getAllLocations();
    LocationDTO updateLocation(LocationDTO locationDTO);
    void deleteLocation(Long id);
    
}
