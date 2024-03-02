package com.backend.santasworkshopbackend.service.impl;

import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ToyDTO;
import com.backend.santasworkshopbackend.entity.Toy;
import com.backend.santasworkshopbackend.repository.ToyRepository;
import com.backend.santasworkshopbackend.service.ToyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ToyServiceImpl implements ToyService{

    private final ToyRepository toyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ToyServiceImpl(ToyRepository toyRepository, ModelMapper modelMapper) {
        this.toyRepository = toyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ToyDTO createToy(ToyDTO toyDTO) {
        Toy toy = modelMapper.map(toyDTO, Toy.class);

        Toy savedToy = toyRepository.save(toy);
        return modelMapper.map(savedToy, Toy.class);
    }

    @Override
    public ToyDTO getToy(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToy'");
    }

    @Override
    public Page<ToyDTO> getAllToys(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllToys'");
    }

    @Override
    public ToyDTO updateToy(Long id, ToyDTO toyDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateToy'");
    }

    @Override
    public void deleteToy(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteToy'");
    }
    
}
