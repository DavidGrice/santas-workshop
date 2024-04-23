package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.LocationDTO;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.service.LocationService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setAddress(locationDTO.getAddress());
        location.setCity(locationDTO.getCity());
        location.setStateProv(locationDTO.getStateProv());
        location.setCountry(locationDTO.getCountry());
        location.setRegion(locationDTO.getRegion());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        Location savedLocation = locationRepository.save(location);
        LocationDTO createdLocation = modelMapper.map(savedLocation, LocationDTO.class);
        return createdLocation;
    }

    @Override
    public LocationDTO getLocation(Long id) {
        Location location;
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if(optionalLocation.isPresent()){
            location = optionalLocation.get();
        } else {
            return null;
        }
        LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
        return locationDTO;
    }

    @Override
    public Page<LocationDTO> getAllLocations(Pageable pagedLocations) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());

        return locationRepository.findAll(pagedLocations).map(location -> modelMapper.map(location, LocationDTO.class));
    }

    @Override
    public LocationDTO updateLocation(LocationDTO location) {
        Location exisitingLocation = locationRepository.findById(location.getId())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        exisitingLocation.setAddress(location.getAddress());
        exisitingLocation.setCity(location.getCity());
        exisitingLocation.setStateProv(location.getStateProv());
        exisitingLocation.setCountry(location.getCountry());
        exisitingLocation.setRegion(location.getRegion());
        exisitingLocation.setLatitude(location.getLatitude());
        exisitingLocation.setLongitude(location.getLongitude());

        Location updatedLocation = locationRepository.save(exisitingLocation);
        LocationDTO updatedLocationDTO = modelMapper.map(updatedLocation, LocationDTO.class);
        return updatedLocationDTO;
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
    
}
