package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.LocationDTO;

@Service
public interface LocationService {

    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocation(Long id);
    Page<LocationDTO> getAllLocations();
    LocationDTO updateLocation(LocationDTO locationDTO);
    void deleteLocation(Long id);
    
}
