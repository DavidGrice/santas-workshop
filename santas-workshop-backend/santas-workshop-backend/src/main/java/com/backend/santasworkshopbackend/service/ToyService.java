package com.backend.santasworkshopbackend.service;

import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.ToyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToyService {

    ToyDTO createToy(ToyDTO toyDTO);
    ToyDTO getToy(Long id);
    List<ToyDTO> getAllToys();
    ToyDTO updateToy(ToyDTO toyDTO);
    void deleteToy(Long id);
    
}
