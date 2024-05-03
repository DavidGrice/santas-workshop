package com.backend.santasworkshopbackend.service;


import java.sql.Date;
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
    Page<ChildDTO> searchChildren(Long id, String firstName, String lastName, Date birthdate, Long statusID, Long locationId, Pageable pagedChild);
    boolean existsByIdAndStatusID_StatusName(Long id, String statusName);
    
}
