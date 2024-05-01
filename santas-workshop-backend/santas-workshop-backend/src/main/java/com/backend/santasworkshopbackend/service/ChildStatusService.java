package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ChildStatusDTO;

@Service
public interface ChildStatusService {

    ChildStatusDTO createStatus(ChildStatusDTO statusDTO);
    ChildStatusDTO getStatus(Long id);
    Page<ChildStatusDTO> getAllStatuses(Pageable pagedStatuses);
    ChildStatusDTO updateStatus(ChildStatusDTO statusDTO);
    void deleteStatus(Long id);
    Page<ChildStatusDTO> searchStatuses(Long id, String statusName, String statusDescription, Pageable pageable);
    boolean existsByChildIdAndStatusName(Long childId, String statusName);
    boolean existsByChildIdAndStatusNameAndStatusDescription(Long childId, String statusName, String statusDescription);
    
}
