package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.ToyDTO;
import com.backend.santasworkshopbackend.entity.Description;
import com.backend.santasworkshopbackend.entity.Toy;
import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.repository.DescriptionRepository;
import com.backend.santasworkshopbackend.repository.RoleRepository;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.repository.UserRepository;
import com.backend.santasworkshopbackend.service.ToyService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

        Description description = modelMapper.map(toyDTO.getDescriptionId(), Description.class);
        User addedBy = modelMapper.map(toyDTO.getAddedById(), User.class);
        User updatedBy = modelMapper.map(toyDTO.getUpdatedById(), User.class);

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

        Toy existingToy = toyRepository.findById(toyDTO.getId()).get();
        existingToy.setName(toyDTO.getName());

        Description description = descriptionRepository.findById(toyDTO.getDescriptionId().getId()).orElseGet(() -> {
            Description newDescription = new Description();
            newDescription.setId(toyDTO.getDescriptionId().getId());
            newDescription.setDescription(toyDTO.getDescriptionId().getDescription());
            return descriptionRepository.save(newDescription);
        });
        existingToy.setDescription(description);

        User addedByUser = userRepository.findById(toyDTO.getAddedById().getId()).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(toyDTO.getAddedById().getId());
            newUser.setFirstName(toyDTO.getAddedById().getFirstName());
            newUser.setLastName(toyDTO.getAddedById().getLastName());
            newUser.setEmail(toyDTO.getAddedById().getEmail());
            newUser.setPassword(toyDTO.getAddedById().getPassword());
            newUser.setRole(roleRepository.findById(toyDTO.getAddedById().getRoleId()).get());
            return userRepository.save(newUser);
        });
        existingToy.setAddedBy(addedByUser);

        existingToy.setAddedDate(toyDTO.getAddedDate());

        User createdByUser = userRepository.findById(toyDTO.getUpdatedById().getId()).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(toyDTO.getAddedById().getId());
            newUser.setFirstName(toyDTO.getAddedById().getFirstName());
            newUser.setLastName(toyDTO.getAddedById().getLastName());
            newUser.setEmail(toyDTO.getAddedById().getEmail());
            newUser.setPassword(toyDTO.getAddedById().getPassword());
            newUser.setRole(roleRepository.findById(toyDTO.getAddedById().getRoleId()).get());
            return userRepository.save(newUser);
        });
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
    
}
