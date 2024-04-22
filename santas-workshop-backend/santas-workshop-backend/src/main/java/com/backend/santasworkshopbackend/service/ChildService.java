package com.backend.santasworkshopbackend.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.santasworkshopbackend.dto.ChildDTO;

@Service
public interface ChildService {

    ChildDTO createChild(ChildDTO childDTO);
    ChildDTO getChild(Long id);
    Page<ChildDTO> getAllChildren(Pageable pagedChild);
    ChildDTO updateChild(ChildDTO childDTO);
    void deleteChild(Long id);
    
}
