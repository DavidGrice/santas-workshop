package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;
import com.backend.santasworkshopbackend.service.DeliveryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    @Autowired
    private DeliveryService deliveryService;
    private final ModelMapper modelMapper;

    @Autowired
    public DeliveryServiceImpl(DeliveryService deliveryService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.deliveryService = deliveryService;
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        DeliveryDTO delivery = modelMapper.map(deliveryDTO, DeliveryDTO.class);
        DeliveryDTO savedDelivery = deliveryService.createDelivery(delivery);
        return modelMapper.map(savedDelivery, DeliveryDTO.class);
    }

    @Override
    public DeliveryDTO getDelivery(Long id) {
        DeliveryDTO delivery = deliveryService.getDelivery(id);
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    @Override
    public Page<DeliveryDTO> getAllDeliveries(Pageable pageable) {
        return deliveryService.getAllDeliveries(pageable).map(delivery -> modelMapper.map(delivery, DeliveryDTO.class));
    }

    @Override
    public DeliveryDTO updateDelivery(Long id, DeliveryDTO deliveryDTO) {
        DeliveryDTO delivery = modelMapper.map(deliveryDTO, DeliveryDTO.class);
        DeliveryDTO updatedDelivery = deliveryService.updateDelivery(id, delivery);
        return modelMapper.map(updatedDelivery, DeliveryDTO.class);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryService.deleteDelivery(id);
    }
    
}
