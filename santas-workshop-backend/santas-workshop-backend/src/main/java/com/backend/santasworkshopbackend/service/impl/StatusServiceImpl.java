package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.StatusDTO;
import com.backend.santasworkshopbackend.entity.Status;
import com.backend.santasworkshopbackend.repository.StatusRepository;
import com.backend.santasworkshopbackend.service.StatusService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(StatusServiceImpl.class);

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatusDTO createStatus(StatusDTO statusDTO) {
        Status status = new Status();

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, StatusDTO.class);
    }

    @Override
    public StatusDTO getStatus(Long id) {
        Optional<Status> status = statusRepository.findById(id);

        if (status.isPresent()) {
            return modelMapper.map(status.get(), StatusDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public Page<StatusDTO> getAllStatuses(Pageable pagedStatuses) {
        Page<Status> statuses = statusRepository.findAll(pagedStatuses);

        return statuses.map(status -> new StatusDTO(status, true));
    }

    @Override
    public StatusDTO updateStatus(StatusDTO statusDTO) {
        Status status = statusRepository.findById(statusDTO.getId()).orElseThrow(() -> new RuntimeException("Status not found"));

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, StatusDTO.class);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
    
}
