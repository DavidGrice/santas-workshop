package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.santasworkshopbackend.dto.LocationDTO;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.service.LocationService;
import com.backend.santasworkshopbackend.specification.LocationSpecification;
import com.backend.santasworkshopbackend.specification.SearchCriteria;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
        location.setZipCode(locationDTO.getZipCode());
        location.setCountry(locationDTO.getCountry());
        location.setRegion(locationDTO.getRegion());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        Location savedLocation = locationRepository.save(location);
        LocationDTO createdLocation = modelMapper.map(savedLocation, LocationDTO.class);
        Logger.info("Location created createLocation(LocationDTO locationDTO)" + createdLocation);
        return createdLocation;
    }

    @Override
    public LocationDTO getLocation(Long id) {
        Location location;
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if(optionalLocation.isPresent()){
            location = optionalLocation.get();
            LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
            Logger.info("Location exists in getLocation(Long id)" + locationDTO);
        return locationDTO;
        } else {
            Logger.error("Error in getLocation(Long id)");
            return null;
        }
    }

    @Override
    public Page<LocationDTO> getAllLocations(Pageable pagedLocations) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());
        Logger.info("Location exists in getAllLocations(Pageable pagedLocations)" + pagedLocations);
        return locationRepository.findAll(pagedLocations).map(location -> modelMapper.map(location, LocationDTO.class));
    }

    @Override
    public LocationDTO updateLocation(LocationDTO location) {
        Location existingLocation = locationRepository.findById(location.getId())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        existingLocation.setAddress(location.getAddress());
        existingLocation.setCity(location.getCity());
        existingLocation.setStateProv(location.getStateProv());
        existingLocation.setZipCode(location.getZipCode());
        existingLocation.setCountry(location.getCountry());
        existingLocation.setRegion(location.getRegion());
        existingLocation.setLatitude(location.getLatitude());
        existingLocation.setLongitude(location.getLongitude());

        Location updatedLocation = locationRepository.save(existingLocation);
        LocationDTO updatedLocationDTO = modelMapper.map(updatedLocation, LocationDTO.class);
        Logger.info("Location exists in updateLocation(LocationDTO location)" + location);
        return updatedLocationDTO;
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
        Logger.info("Location deleted deleteLocation(Long id)" + id);
    }

    @Override
    public Page<LocationDTO> searchLocations(Long id, String address, String city, String stateProv, String zipCode,
            String country, String region, Double latitude, Double longitude, Pageable pagedLocations) {
            Specification<Location> spec = Specification.where(null);

            if (id != null) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("id", ":", id)));
            }
            if (StringUtils.hasText(address)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("address", ":", address)));
            }
            if (StringUtils.hasText(city)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("city", ":", city)));
            }
            if (StringUtils.hasText(stateProv)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("stateProv", ":", stateProv)));
            }
            if (StringUtils.hasText(zipCode)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("zipCode", ":", zipCode)));
            }
            if (StringUtils.hasText(country)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("country", ":", country)));
            }
            if (StringUtils.hasText(region)) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("region", ":", region)));
            }
            if (latitude != null) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("latitude", ":", latitude)));
            }
            if (longitude != null) {
                spec = spec.and(new LocationSpecification(new SearchCriteria("longitude", ":", longitude)));
            }

            return locationRepository.findAll(spec, pagedLocations).map(location -> modelMapper.map(location, LocationDTO.class));
    }

    @Override
    public boolean existsByAddress(String address) {
        return locationRepository.existsByAddress(address);
    }

    @Override
    public boolean existsByCity(String city) {
        return locationRepository.existsByCity(city);
    }

    @Override
    public boolean existsByStateProv(String stateProv) {
        return locationRepository.existsByStateProv(stateProv);
    }

    @Override
    public boolean existsByZipCode(String zipCode) {
        return locationRepository.existsByZipCode(zipCode);
    }

    @Override
    public boolean existsByCountry(String country) {
        return locationRepository.existsByCountry(country);
    }

    @Override
    public boolean existsByRegion(String region) {
        return locationRepository.existsByRegion(region);
    }

    @Override
    public boolean existsByLatitudeAndLongitude(Double latitude, Double longitude) {
        return locationRepository.existsByLatitudeAndLongitude(latitude, longitude);
    }
    
}
