package com.backend.santasworkshopbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.backend.santasworkshopbackend.dto.LocationDTO;
import com.backend.santasworkshopbackend.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/createLocation")
    public ResponseEntity<LocationDTO> createLocation(@Validated
                                                      @RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocation = locationService.createLocation(locationDTO);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @GetMapping("/getLocation/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable("id") Long id) {
        LocationDTO location = locationService.getLocation(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/getAllLocations")
    public ResponseEntity<Page<LocationDTO>> getAllLocations(Pageable pagedLocations) {
        Page <LocationDTO> location = locationService.getAllLocations(pagedLocations);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@Validated
                                                      @PathVariable("id") Long id,
                                                      @RequestBody LocationDTO location) {

        location.setId(id);
        LocationDTO updatedLocation = locationService.updateLocation(location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<Map<String, String>> deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteLocation(id);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "Location deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
