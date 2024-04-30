package com.backend.santasworkshopbackend.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ChildDTO;
import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.entity.Status;
import com.backend.santasworkshopbackend.repository.ChildRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.StatusRepository;
import com.backend.santasworkshopbackend.service.ChildService;
import com.backend.santasworkshopbackend.specification.ChildSpecification;
import com.backend.santasworkshopbackend.specification.SearchCriteria;

@Service
public class ChildServiceImpl implements ChildService {

    private StatusRepository statusRepository;
    private ChildRepository childRepository;
    private LocationRepository locationRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository, ModelMapper modelMapper, StatusRepository statusRepository, LocationRepository locationRepository) {
        this.childRepository = childRepository;
        this.modelMapper = modelMapper;
        this.statusRepository = statusRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public ChildDTO createChild(ChildDTO childDTO) {
        Child child = new Child();

        child.setFirstName(childDTO.getFirstName());
        child.setLastName(childDTO.getLastName());
        child.setAge(childDTO.getAge());

        Status status = statusRepository.findById(childDTO.getStatusID())
            .orElseThrow(() -> new RuntimeException("Status not found"));
        child.setStatusID(status);

        Location location = locationRepository.findById(childDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        child.setLocationID(location);

        child = childRepository.save(child);

        ChildDTO createdChild = modelMapper.map(child, ChildDTO.class);
        return createdChild;
    }

    @Override
    public ChildDTO getChild(Long id) {
        Child child;
        Optional<Child> optionalChild = childRepository.findById(id);
        if(optionalChild.isPresent()){
            child = optionalChild.get();
        } else {
            throw new RuntimeException("Child not found for id: " + id);
        }
        return modelMapper.map(child, ChildDTO.class);
    }

    @Override
    public Page<ChildDTO> getAllChildren(Pageable pagedChild) {
        // List<Child> children = childRepository.findAll();
        // return children.stream().map(child -> modelMapper.map(child, ChildDTO.class)).collect(Collectors.toList());
        return childRepository.findAll(pagedChild).map(child -> modelMapper.map(child, ChildDTO.class));
    }

    @Override
    public ChildDTO updateChild(ChildDTO childDTO) {

        Child existingChild = childRepository.findById(childDTO.getId())
            .orElseThrow(() -> new RuntimeException("Child not found"));
        existingChild.setFirstName(childDTO.getFirstName());
        existingChild.setLastName(childDTO.getLastName());
        existingChild.setAge(childDTO.getAge());

        Status status = statusRepository.findById(childDTO.getStatusID())
            .orElseThrow(() -> new RuntimeException("Status not found"));
        existingChild.setStatusID(status);

        Location location = locationRepository.findById(childDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        existingChild.setLocationID(location);

        Child updatedChild = childRepository.save(existingChild);
        return modelMapper.map(updatedChild, ChildDTO.class);
    }

    @Override
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }

    @Override
    public Page<ChildDTO> searchChildren(String firstName, String lastName, Integer age, Long statusId, Long locationId, Pageable pageable) {
        Specification<Child> spec = Specification.where(null);

        if (firstName != null) {
            Specification<Child> specFirstName = new ChildSpecification(new SearchCriteria("firstName", ":", firstName));
            spec = spec.and(specFirstName);
        }

        if (lastName != null) {
            Specification<Child> specLastName = new ChildSpecification(new SearchCriteria("lastName", ":", lastName));
            spec = spec.and(specLastName);
        }

        if (age != null) {
            Specification<Child> specAge = new ChildSpecification(new SearchCriteria("age", ":", age));
            spec = spec.and(specAge);
        }

        if (statusId != null) {
            Specification<Child> specStatus = new ChildSpecification(new SearchCriteria("status.id", ":", statusId));
            spec = spec.and(specStatus);
        }

        if (locationId != null) {
            Specification<Child> specLocation = new ChildSpecification(new SearchCriteria("location.id", ":", locationId));
            spec = spec.and(specLocation);
        }

        Page<Child> children = childRepository.findAll(spec, pageable);
        return children.map(child -> modelMapper.map(child, ChildDTO.class));
    }
    
}
