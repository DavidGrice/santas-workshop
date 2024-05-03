package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.santasworkshopbackend.dto.DeliveryStatusDTO;
import com.backend.santasworkshopbackend.entity.DeliveryStatus;
import com.backend.santasworkshopbackend.repository.DeliveryStatusRepository;
import com.backend.santasworkshopbackend.service.DeliveryStatusService;
import com.backend.santasworkshopbackend.specification.DeliveryStatusSpecification;
import com.backend.santasworkshopbackend.specification.SearchCriteria;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {

    private DeliveryStatusRepository statusRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DeliveryStatusServiceImpl.class);

    @Autowired
    public DeliveryStatusServiceImpl(DeliveryStatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeliveryStatusDTO createStatus(DeliveryStatusDTO statusDTO) {
        DeliveryStatus status = new DeliveryStatus();

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, DeliveryStatusDTO.class);
    }

    @Override
    public DeliveryStatusDTO getStatus(Long id) {
        Optional<DeliveryStatus> status = statusRepository.findById(id);

        if (status.isPresent()) {
            return modelMapper.map(status.get(), DeliveryStatusDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public Page<DeliveryStatusDTO> getAllStatuses(Pageable pagedStatuses) {
        Page<DeliveryStatus> statuses = statusRepository.findAll(pagedStatuses);

        return statuses.map(status -> new DeliveryStatusDTO(status, true));
    }

    @Override
    public DeliveryStatusDTO updateStatus(DeliveryStatusDTO statusDTO) {
        DeliveryStatus status = statusRepository.findById(statusDTO.getId()).orElseThrow(() -> new RuntimeException("Status not found"));

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, DeliveryStatusDTO.class);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Page<DeliveryStatusDTO> searchStatuses(Long id, String statusName, String statusDescription, Pageable pagedStatuses) {
        Specification<DeliveryStatus> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(new DeliveryStatusSpecification(new SearchCriteria("id", ":", id)));
        }
        if (StringUtils.hasText(statusName)) {
            spec = spec.and(new DeliveryStatusSpecification(new SearchCriteria("statusName", ":", statusName)));
        }
        if (StringUtils.hasText(statusDescription)) {
            spec = spec.and(new DeliveryStatusSpecification(new SearchCriteria("statusDescription", ":", statusDescription)));
        }

        return statusRepository.findAll(spec, pagedStatuses).map(status -> modelMapper.map(status, DeliveryStatusDTO.class));
    }

    @Override
    public boolean existsByStatusName(String statusName) {
        return statusRepository.existsByStatusName(statusName);
    }
    
}
