package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.DeliveryDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO getDelivery(Long id);
    List<DeliveryDTO> getAllDeliveries();
    DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO);
    void deleteDelivery(Long id);
    
}
