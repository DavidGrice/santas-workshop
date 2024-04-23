package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.StatusDTO;

@Service
public interface StatusService {

    StatusDTO createStatus(StatusDTO statusDTO);
    StatusDTO getStatus(Long id);
    Page<StatusDTO> getAllStatuses(Pageable pagedStatuses);
    StatusDTO updateStatus(StatusDTO statusDTO);
    void deleteStatus(Long id);
    
}
