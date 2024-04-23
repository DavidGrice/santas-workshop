package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.DescriptionDTO;
import com.backend.santasworkshopbackend.entity.Description;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.service.DescriptionService;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private DescriptionRepository descriptionRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DescriptionServiceImpl.class);

    @Autowired
    public DescriptionServiceImpl(DescriptionRepository descriptionRepository, ModelMapper modelMapper) {
        this.descriptionRepository = descriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DescriptionDTO createDescription(DescriptionDTO descriptionDTO) {
        Description description = modelMapper.map(descriptionDTO, Description.class);
        Description savedDescription = descriptionRepository.save(description);
        DescriptionDTO savedDescriptionDTO = modelMapper.map(savedDescription, DescriptionDTO.class);
        return savedDescriptionDTO;
    }

    @Override
    public DescriptionDTO getDescription(Long id) {
        Description description;
        Optional<Description> optionalDescription = descriptionRepository.findById(id);
        if (optionalDescription.isPresent()) {
            description = optionalDescription.get();
        } else {
            throw new RuntimeException("Description not found");
        }
        return modelMapper.map(description, DescriptionDTO.class);
    }

    @Override
    public Page<DescriptionDTO> getAllDescriptions(Pageable pagedDescriptions) {
        // List<Description> descriptions = descriptionRepository.findAll();
        // return descriptions.stream().map(description -> modelMapper.map(description, DescriptionDTO.class)).collect(Collectors.toList());

        return descriptionRepository.findAll(pagedDescriptions).map(description -> modelMapper.map(description, DescriptionDTO.class));
    }

    @Override
    public DescriptionDTO updateDescription(DescriptionDTO description) {

        Description existingDescription = descriptionRepository.findById(description.getId())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        existingDescription.setName(description.getName());
        existingDescription.setDescription(description.getDescription());
        Description updatedDescription = descriptionRepository.save(existingDescription);

        return modelMapper.map(updatedDescription, DescriptionDTO.class);
    }

    @Override
    public void deleteDescription(Long id) {
        descriptionRepository.deleteById(id);
    }
    
}
