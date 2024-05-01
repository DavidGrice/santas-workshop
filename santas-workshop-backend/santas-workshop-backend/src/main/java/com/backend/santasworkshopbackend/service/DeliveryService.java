package com.backend.santasworkshopbackend.service;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;

@Service
public interface DeliveryService {
    
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO getDelivery(Long id);
    Page<DeliveryDTO> getAllDeliveries(Pageable pageable);
    DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO);
    void deleteDelivery(Long id);
    Page<DeliveryDTO> searchDeliveries(Long id, Long childId, Long toyId, Long deliveryStatusId, Date deliveryDate, Pageable pageable);
    boolean existsByChildIdAndToyId(Long childId, Long toyId);
    boolean existsByChildIdAndToyIdAndDeliveryStatusId(Long childId, Long toyId, Long deliveryStatusId);
    boolean existsByChildIdAndToyIdAndDeliveryStatusIdAndDeliveryDate(Long childId, Long toyId, Long deliveryStatusId, Date deliveryDate);
    
}
