package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.DescriptionDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DescriptionService {

    DescriptionDTO createDescription(DescriptionDTO descriptionDTO);
    DescriptionDTO getDescription(Long id);
    List<DescriptionDTO> getAllDescriptions();
    DescriptionDTO updateDescription(DescriptionDTO descriptionDTO);
    void deleteDescription(Long id);
    
}
