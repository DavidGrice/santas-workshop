package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DeliveryStatusDTO;

@Service
public interface DeliveryStatusService {

    DeliveryStatusDTO createStatus(DeliveryStatusDTO statusDTO);
    DeliveryStatusDTO getStatus(Long id);
    Page<DeliveryStatusDTO> getAllStatuses(Pageable pagedStatuses);
    DeliveryStatusDTO updateStatus(DeliveryStatusDTO statusDTO);
    void deleteStatus(Long id);
    Page<DeliveryStatusDTO> searchStatuses(Long id, String statusName, String statusDescription, Pageable pagedStatuses);
    boolean existsByStatusName(String statusName);
    
}
