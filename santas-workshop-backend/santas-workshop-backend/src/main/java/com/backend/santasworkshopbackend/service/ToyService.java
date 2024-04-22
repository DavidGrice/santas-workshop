package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ToyDTO;

@Service
public interface ToyService {

    ToyDTO createToy(ToyDTO toyDTO);
    ToyDTO getToy(Long id);
    Page<ToyDTO> getAllToys(Pageable pagedToys);
    ToyDTO updateToy(ToyDTO toyDTO);
    void deleteToy(Long id);
    
}
