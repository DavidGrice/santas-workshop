package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.DescriptionDTO;

@Service
public interface DescriptionService {

    DescriptionDTO createDescription(DescriptionDTO descriptionDTO);
    DescriptionDTO getDescription(Long id);
    Page<DescriptionDTO> getAllDescriptions(Pageable pagedDescriptions);
    DescriptionDTO updateDescription(DescriptionDTO descriptionDTO);
    void deleteDescription(Long id);
    Page<DescriptionDTO> searchDescriptions(Long id, String name, String description, Pageable pagedDescriptions);
    boolean existsByName(String name);
    boolean existsByDescription(String description);
    
}
