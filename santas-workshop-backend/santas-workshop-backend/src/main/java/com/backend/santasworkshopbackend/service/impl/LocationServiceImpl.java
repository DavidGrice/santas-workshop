package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.LocationDTO;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.repository.DeliveryRepository;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;
import com.backend.santasworkshopbackend.service.LocationService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class LocationServiceImpl implements LocationService {

    private DeliveryRepository deliveryRepository;
    private DescriptionRepository descriptionRepository;
    private LocationRepository locationRepository;
    private RoleRepository roleRepository;
    private ToyRepository toyRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = new Location();

        location.setAddress(locationDTO.getAddress());
        location.setCity(locationDTO.getCity());
        location.setStateProv(locationDTO.getStateProv());
        location.setCountry(locationDTO.getCountry());
        location.setRegion(locationDTO.getRegion());
        location.setCoordinates(locationDTO.getCoordinates());

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
    public LocationDTO updateLocation(LocationDTO locationDTO) {
        Location location = new Location();

        location.setAddress(locationDTO.getAddress());
        location.setCity(locationDTO.getCity());
        location.setStateProv(locationDTO.getStateProv());
        location.setCountry(locationDTO.getCountry());
        location.setRegion(locationDTO.getRegion());
        location.setCoordinates(locationDTO.getCoordinates());

        Location updatedLocation = locationRepository.save(location);
        LocationDTO updatedLocationDTO = modelMapper.map(updatedLocation, LocationDTO.class);
        return updatedLocationDTO;
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
    
}
