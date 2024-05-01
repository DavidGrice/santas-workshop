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

    @GetMapping("/searchLocations")
    public ResponseEntity<Page<LocationDTO>> searchLocations(@RequestParam(required = false) Long id,
                                                             @RequestParam(required = false) String address,
                                                             @RequestParam(required = false) String city,
                                                             @RequestParam(required = false) String stateProv,
                                                             @RequestParam(required = false) String zipCode,
                                                             @RequestParam(required = false) String country,
                                                             @RequestParam(required = false) String region,
                                                             @RequestParam(required = false) Double latitude,
                                                             @RequestParam(required = false) Double longitude,
                                                             Pageable pagedLocations) {
        Page<LocationDTO> location = locationService.searchLocations(id, address, city, stateProv, zipCode, country, region, latitude, longitude, pagedLocations);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/existsByAddress")
    public ResponseEntity<Boolean> existsByAddress(@RequestParam String address) {
        return new ResponseEntity<>(locationService.existsByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/existsByCity")
    public ResponseEntity<Boolean> existsByCity(@RequestParam String city) {
        return new ResponseEntity<>(locationService.existsByCity(city), HttpStatus.OK);
    }

    @GetMapping("/existsByStateProv")
    public ResponseEntity<Boolean> existsByStateProv(@RequestParam String stateProv) {
        return new ResponseEntity<>(locationService.existsByStateProv(stateProv), HttpStatus.OK);
    }

    @GetMapping("/existsByZipCode")
    public ResponseEntity<Boolean> existsByZipCode(@RequestParam String zipCode) {
        return new ResponseEntity<>(locationService.existsByZipCode(zipCode), HttpStatus.OK);
    }

    @GetMapping("/existsByCountry")
    public ResponseEntity<Boolean> existsByCountry(@RequestParam String country) {
        return new ResponseEntity<>(locationService.existsByCountry(country), HttpStatus.OK);
    }

    @GetMapping("/existsByRegion")
    public ResponseEntity<Boolean> existsByRegion(@RequestParam String region) {
        return new ResponseEntity<>(locationService.existsByRegion(region), HttpStatus.OK);
    }

    @GetMapping("/existsByLatitudeAndLongitude")
    public ResponseEntity<Boolean> existsByLatitudeAndLongitude(@RequestParam Double latitude,
                                                               @RequestParam Double longitude) {
        return new ResponseEntity<>(locationService.existsByLatitudeAndLongitude(latitude, longitude), HttpStatus.OK);
    }
    
}
