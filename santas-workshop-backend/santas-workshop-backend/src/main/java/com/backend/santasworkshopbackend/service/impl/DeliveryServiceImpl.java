package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;
import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.entity.Delivery;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.entity.Status;
import com.backend.santasworkshopbackend.entity.Toy;
import com.backend.santasworkshopbackend.repository.ChildRepository;
import com.backend.santasworkshopbackend.repository.DeliveryRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.StatusRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.service.DeliveryService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    private StatusRepository statusRepository;
    private ChildRepository childRepository;
    private DeliveryRepository deliveryRepository;
    private LocationRepository locationRepository;
    private ToyRepository toyRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, ModelMapper modelMapper, StatusRepository statusRepository, ChildRepository childRepository, LocationRepository locationRepository, ToyRepository toyRepository) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;
        this.statusRepository = statusRepository;
        this.childRepository = childRepository;
        this.locationRepository = locationRepository;
        this.toyRepository = toyRepository;
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();

        Child child = childRepository.findById(deliveryDTO.getChildID())
            .orElseThrow(() -> new RuntimeException("Child not found"));
        delivery.setChildID(child);

        Location location = locationRepository.findById(deliveryDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        delivery.setLocationID(location);

        Toy toy = toyRepository.findById(deliveryDTO.getToyID())
            .orElseThrow(() -> new RuntimeException("Toy not found"));
        delivery.setToyID(toy);

        Status status = statusRepository.findById(deliveryDTO.getStatusID())
            .orElseThrow(() -> new RuntimeException("Status not found"));
        delivery.setStatusID(status);

        delivery.setDeliveredDate(deliveryDTO.getDeliveredDate());

        Delivery deliveryMade = deliveryRepository.save(delivery);

        DeliveryDTO deliveredDTO = modelMapper.map(deliveryMade, DeliveryDTO.class);
        
        return deliveredDTO;
    }

    @Override
    public DeliveryDTO getDelivery(Long id) {
        Delivery delivery;
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        if(optionalDelivery.isPresent()){
            delivery = optionalDelivery.get();
            
        } else {
            return null;
        }
        DeliveryDTO deliveryDTO = modelMapper.map(delivery, DeliveryDTO.class);
        return deliveryDTO;
    }

    @Override
    public Page<DeliveryDTO> getAllDeliveries(Pageable pagedToys) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());

        return deliveryRepository.findAll(pagedToys).map(delivery -> modelMapper.map(delivery, DeliveryDTO.class));
    }

    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryDTO.getId())
            .orElseThrow(() -> new RuntimeException("Delivery not found"));
        existingDelivery.setDeliveredDate(deliveryDTO.getDeliveredDate());

        Status status = statusRepository.findById(deliveryDTO.getStatusID())
            .orElseThrow(() -> new RuntimeException("Status not found"));
        existingDelivery.setStatusID(status);

        Child child = childRepository.findById(deliveryDTO.getChildID())
            .orElseThrow(() -> new RuntimeException("Child not found"));
        existingDelivery.setChildID(child);

        Location location = locationRepository.findById(deliveryDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        existingDelivery.setLocationID(location);

        Toy toy = toyRepository.findById(deliveryDTO.getToyID())
            .orElseThrow(() -> new RuntimeException("Toy not found"));
        existingDelivery.setToyID(toy);

        Delivery updatedDelivery = deliveryRepository.save(existingDelivery);
        return modelMapper.map(updatedDelivery, DeliveryDTO.class);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
    
}
