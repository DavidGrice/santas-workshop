package com.backend.santasworkshopbackend.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ChildDTO;
import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.repository.ChildRepository;
import com.backend.santasworkshopbackend.repository.DeliveryRepository;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;
import com.backend.santasworkshopbackend.service.ChildService;

@Service
public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;
    private DeliveryRepository deliveryRepository;
    private DescriptionRepository descriptionRepository;
    private LocationRepository locationRepository;
    private RoleRepository roleRepository;
    private ToyRepository toyRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Override
    public ChildDTO createChild(ChildDTO childDTO) {
        Child child = new Child();

        child.setFirstName(childDTO.getFirstName());
        child.setLastName(childDTO.getLastName());
        child.setAge(childDTO.getAge());
        child.setStatusType(childDTO.getStatusType());

        Location location = modelMapper.map(childDTO.getChildLocation(), Location.class);
        location = locationRepository.save(location);
        child.setLocation(location);

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

        Child existingChild = childRepository.findById(childDTO.getId()).get();
        existingChild.setFirstName(childDTO.getFirstName());
        existingChild.setLastName(childDTO.getLastName());
        existingChild.setAge(childDTO.getAge());
        existingChild.setStatusType(childDTO.getStatusType());

        Location location = locationRepository.findById(childDTO.getChildLocation().getId()).orElseGet(() -> {
            Location newLocation = new Location();
            newLocation.setAddress(childDTO.getChildLocation().getAddress());
            newLocation.setCity(childDTO.getChildLocation().getCity());
            newLocation.setStateProv(childDTO.getChildLocation().getStateProv());
            newLocation.setCountry(childDTO.getChildLocation().getCountry());
            newLocation.setRegion(childDTO.getChildLocation().getRegion());
            newLocation.setCoordinates(childDTO.getChildLocation().getCoordinates());
            return newLocation;
        });
        existingChild.setLocation(location);

        Child updatedChild = childRepository.save(existingChild);
        return modelMapper.map(updatedChild, ChildDTO.class);
    }

    @Override
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }
    
}
