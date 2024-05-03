package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ChildStatusDTO;
import com.backend.santasworkshopbackend.entity.ChildStatus;
import com.backend.santasworkshopbackend.repository.ChildStatusRepository;
import com.backend.santasworkshopbackend.service.ChildStatusService;
import com.backend.santasworkshopbackend.specification.ChildStatusSpecification;
import com.backend.santasworkshopbackend.specification.SearchCriteria;
import org.springframework.util.StringUtils;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class ChildStatusServiceImpl implements ChildStatusService {

    private ChildStatusRepository statusRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(ChildStatusServiceImpl.class);

    @Autowired
    public ChildStatusServiceImpl(ChildStatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChildStatusDTO createStatus(ChildStatusDTO statusDTO) {
        ChildStatus status = new ChildStatus();

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, ChildStatusDTO.class);
    }

    @Override
    public ChildStatusDTO getStatus(Long id) {
        Optional<ChildStatus> status = statusRepository.findById(id);

        if (status.isPresent()) {
            return modelMapper.map(status.get(), ChildStatusDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public Page<ChildStatusDTO> getAllStatuses(Pageable pagedStatuses) {
        Page<ChildStatus> statuses = statusRepository.findAll(pagedStatuses);

        return statuses.map(status -> new ChildStatusDTO(status, true));
    }

    @Override
    public ChildStatusDTO updateStatus(ChildStatusDTO statusDTO) {
        ChildStatus status = statusRepository.findById(statusDTO.getId()).orElseThrow(() -> new RuntimeException("Status not found"));

        status.setStatusName(statusDTO.getStatusName());
        status.setStatusDescription(statusDTO.getStatusDescription());

        statusRepository.save(status);

        return modelMapper.map(status, ChildStatusDTO.class);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Page<ChildStatusDTO> searchStatuses(Long id, String statusName, String statusDescription, Pageable pageable) {
        Specification<ChildStatus> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(new ChildStatusSpecification(new SearchCriteria("id", ":", id)));
        }
        if (StringUtils.hasText(statusName)) {
            spec = spec.and(new ChildStatusSpecification(new SearchCriteria("stat", ":", statusName)));
        }
        if (StringUtils.hasText(statusDescription)) {
            spec = spec.and(new ChildStatusSpecification(new SearchCriteria("status_description", ":", statusDescription)));
        }

        return statusRepository.findAll(spec, pageable).map(status -> new ChildStatusDTO(status, true));
    }

    @Override
    public boolean existsByIdAndStatusName(Long id, String statusName) {
        Logger.info(statusName);
        return statusRepository.existsByIdAndStatusName(id, statusName);
    }

    @Override
    public boolean existsByIdAndStatusNameAndStatusDescription(Long id, String statusName, String statusDescription) {
        Logger.info(statusName);
        Logger.info(statusDescription);
        return statusRepository.existsByIdAndStatusNameAndStatusDescription(id, statusName, statusDescription);
    }
    
}
