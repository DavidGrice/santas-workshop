package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.santasworkshopbackend.dto.ToyDTO;
import com.backend.santasworkshopbackend.entity.Description;
import com.backend.santasworkshopbackend.entity.Toy;
import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;
import com.backend.santasworkshopbackend.service.ToyService;
import com.backend.santasworkshopbackend.specification.SearchCriteria;
import com.backend.santasworkshopbackend.specification.ToySpecification;

import java.sql.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class ToyServiceImpl implements ToyService{

    private DescriptionRepository descriptionRepository;
    private RoleRepository roleRepository;
    private ToyRepository toyRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(DescriptionServiceImpl.class);

    @Autowired
    public ToyServiceImpl(DescriptionRepository descriptionRepository, RoleRepository roleRepository, ToyRepository toyRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.descriptionRepository = descriptionRepository;
        this.roleRepository = roleRepository;
        this.toyRepository = toyRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ToyDTO createToy(ToyDTO toyDTO) {

        Toy toy = new Toy();

        Description description = descriptionRepository.findById(toyDTO.getDescriptionID())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        User addedBy = userRepository.findById(toyDTO.getAddedById())
            .orElseThrow(() -> new RuntimeException("User not found"));

        User updatedBy = userRepository.findById(toyDTO.getUpdatedById())
            .orElseThrow(() -> new RuntimeException("User not found"));

        description = descriptionRepository.save(description);

        toy.setName(toyDTO.getName());
        toy.setDescription(description);
        toy.setAddedBy(addedBy);
        toy.setAddedDate(toyDTO.getAddedDate());
        toy.setUpdatedBy(updatedBy);
        toy.setUpdatedDate(toyDTO.getUpdatedDate());
        toy.setQuantity(toyDTO.getQuantity());

        Toy createdToy = toyRepository.save(toy);

        ToyDTO savedToyDTO = modelMapper.map(createdToy, ToyDTO.class);
        return savedToyDTO;
    }

    @Override
    public ToyDTO getToy(Long id) {
        Toy toy;
        Optional <Toy> optionalToy = toyRepository.findById(id);
        if(optionalToy.isPresent()){
            toy = optionalToy.get();
        } else {
            throw new RuntimeException("Toy not found for id: " + id);
        }
        return modelMapper.map(toy, ToyDTO.class);
    }

    @Override
    public Page<ToyDTO> getAllToys(Pageable pagedToys) {

        // List<Toy> toys = toyRepository.findAll();
        // return toys.stream().map(toy -> modelMapper.map(toy, ToyDTO.class)).collect(Collectors.toList());

        return toyRepository.findAll(pagedToys).map(toy -> modelMapper.map(toy, ToyDTO.class));
    }

    @Override
    public ToyDTO updateToy(ToyDTO toyDTO) {

        Toy existingToy = toyRepository.findById(toyDTO.getId())
            .orElseThrow(() -> new RuntimeException("Toy not found"));


        Description description = descriptionRepository.findById(toyDTO.getDescriptionID())
            .orElseThrow(() -> new RuntimeException("Description not found"));

        existingToy.setDescription(description);

        User addedByUser = userRepository.findById(toyDTO.getAddedById())
            .orElseThrow(() -> new RuntimeException("User not found"));
        existingToy.setAddedBy(addedByUser);

        existingToy.setAddedDate(toyDTO.getAddedDate());

        User createdByUser = userRepository.findById(toyDTO.getUpdatedById())
            .orElseThrow(() -> new RuntimeException("User not found"));
        existingToy.setUpdatedBy(createdByUser);

        existingToy.setUpdatedDate(toyDTO.getUpdatedDate());
        existingToy.setQuantity(toyDTO.getQuantity());

        Toy savedToy = toyRepository.save(existingToy);
        return modelMapper.map(savedToy, ToyDTO.class);
    }

    @Override
    public void deleteToy(Long id) {
        toyRepository.deleteById(id);
    }

    @Override
    public Page<ToyDTO> searchToys(String name, Long descriptionID, Long addedBy, Date addedDate, Long updatedBy, Date updatedDate, Long quantity, Pageable pagedToys) {
        Specification<Toy> spec = Specification.where(null);

        if (StringUtils.hasText(name)) {
            spec = spec.and(new ToySpecification(new SearchCriteria("name", ":", name)));
        }
        if (descriptionID != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("description", ":", descriptionID)));
        }
        if (addedBy != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("addedBy", ":", addedBy)));
        }
        if (addedDate != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("addedDate", ":", addedDate)));
        }
        if (updatedBy != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("updatedBy", ":", updatedBy)));
        }
        if (updatedDate != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("updatedDate", ":", updatedDate)));
        }
        if (quantity != null) {
            spec = spec.and(new ToySpecification(new SearchCriteria("quantity", ":", quantity)));
        }

        return toyRepository.findAll(spec, pagedToys).map(toy -> modelMapper.map(toy, ToyDTO.class));
    }

    @Override
    public boolean existsByName(String name) {
        return toyRepository.existsByName(name);
    }
    
}
