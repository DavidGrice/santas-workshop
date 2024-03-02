package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;

@Service
public interface DeliveryService {
    
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO getDelivery(Long id);
    Page<DeliveryDTO> getAllDeliveries(Pageable pageable);
    DeliveryDTO updateDelivery(Long id, DeliveryDTO deliveryDTO);
    void deleteDelivery(Long id);
    
}
