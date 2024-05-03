package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;
import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.entity.Delivery;
import com.backend.santasworkshopbackend.entity.DeliveryStatus;
import com.backend.santasworkshopbackend.entity.Location;
import com.backend.santasworkshopbackend.entity.Wishlist;
import com.backend.santasworkshopbackend.repository.ChildRepository;
import com.backend.santasworkshopbackend.repository.DeliveryRepository;
import com.backend.santasworkshopbackend.repository.DeliveryStatusRepository;
import com.backend.santasworkshopbackend.repository.LocationRepository;
import com.backend.santasworkshopbackend.repository.WishlistRepository;
import com.backend.santasworkshopbackend.service.DeliveryService;
import com.backend.santasworkshopbackend.specification.DeliverySpecification;
import com.backend.santasworkshopbackend.specification.SearchCriteria;

import java.sql.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    private DeliveryStatusRepository statusRepository;
    private ChildRepository childRepository;
    private DeliveryRepository deliveryRepository;
    private LocationRepository locationRepository;
    private WishlistRepository wishlistRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, ModelMapper modelMapper, DeliveryStatusRepository statusRepository, ChildRepository childRepository, LocationRepository locationRepository, WishlistRepository wishlistRepository) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;
        this.statusRepository = statusRepository;
        this.childRepository = childRepository;
        this.locationRepository = locationRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Logger.info("DeliveryDTO: ", deliveryDTO.getChildID(), deliveryDTO.getLocationID(), deliveryDTO.getWishlistID(), deliveryDTO.getStatusID(), deliveryDTO.getDeliveredDate());
        Delivery delivery = new Delivery();

        Child child = childRepository.findById(deliveryDTO.getChildID())
            .orElseThrow(() -> new RuntimeException("Child not found"));
        delivery.setChildID(child);

        Location location = locationRepository.findById(deliveryDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        delivery.setLocationID(location);

        Wishlist wishlist = wishlistRepository.findById(deliveryDTO.getWishlistID())
            .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        delivery.setWishlistID(wishlist);

        DeliveryStatus status = statusRepository.findById(deliveryDTO.getStatusID())
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
    public Page<DeliveryDTO> getAllDeliveries(Pageable pagedDeliveries) {

        // List<wishlist> wishlists = wishlistRepository.findAll();
        // return wishlists.stream().map(wishlist -> modelMapper.map(wishlist, wishlistDTO.class)).collect(Collectors.toList());

        return deliveryRepository.findAll(pagedDeliveries).map(delivery -> modelMapper.map(delivery, DeliveryDTO.class));
    }

    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryDTO.getId())
            .orElseThrow(() -> new RuntimeException("Delivery not found"));
        existingDelivery.setDeliveredDate(deliveryDTO.getDeliveredDate());

        DeliveryStatus status = statusRepository.findById(deliveryDTO.getStatusID())
            .orElseThrow(() -> new RuntimeException("Status not found"));
        existingDelivery.setStatusID(status);

        Child child = childRepository.findById(deliveryDTO.getChildID())
            .orElseThrow(() -> new RuntimeException("Child not found"));
        existingDelivery.setChildID(child);

        Location location = locationRepository.findById(deliveryDTO.getLocationID())
            .orElseThrow(() -> new RuntimeException("Location not found"));
        existingDelivery.setLocationID(location);

        Wishlist wishlist = wishlistRepository.findById(deliveryDTO.getWishlistID())
            .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        existingDelivery.setWishlistID(wishlist);

        Delivery updatedDelivery = deliveryRepository.save(existingDelivery);
        return modelMapper.map(updatedDelivery, DeliveryDTO.class);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public Page<DeliveryDTO> searchDeliveries(Long id, Long childId, Long wishlistId, Long deliveryStatusId, Date deliveryDate, Pageable pagedDeliveries) {
        Specification<Delivery> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(new DeliverySpecification(new SearchCriteria("id", ":", id)));
        }
        if (childId != null) {
            spec = spec.and(new DeliverySpecification(new SearchCriteria("childID", ":", childId)));
        }
        if (wishlistId != null) {
            spec = spec.and(new DeliverySpecification(new SearchCriteria("wishlistID", ":", wishlistId)));
        }
        if (deliveryStatusId != null) {
            spec = spec.and(new DeliverySpecification(new SearchCriteria("statusID", ":", deliveryStatusId)));
        }
        if (deliveryDate != null) {
            spec = spec.and(new DeliverySpecification(new SearchCriteria("deliveredDate", ":", deliveryDate)));
        }

        return deliveryRepository.findAll(pagedDeliveries).map(delivery -> modelMapper.map(delivery, DeliveryDTO.class));
    }

    @Override
    public boolean existsByChildID_IdAndWishlistID_Id(Long childId, Long wishlistId) {
        return deliveryRepository.existsByChildID_IdAndWishlistID_Id(childId, wishlistId);
    }

    @Override
    public boolean existsByChildID_IdAndWishlistID_IdAndStatusID_Id(Long childId, Long wishlistId, Long deliveryStatusId) {
        return deliveryRepository.existsByChildID_IdAndWishlistID_IdAndStatusID_Id(childId, wishlistId, deliveryStatusId);
    }

    @Override
    public boolean existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate(Long childId, Long wishlistId, Long deliveryStatusId, Date deliveryDate) {
        return deliveryRepository.existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate(childId, wishlistId, deliveryStatusId, deliveryDate);
    }
    
}
