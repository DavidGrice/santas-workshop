package com.backend.santasworkshopbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.backend.santasworkshopbackend.dto.DescriptionDTO;
import com.backend.santasworkshopbackend.service.DescriptionService;

@RestController
@RequestMapping("/api/description")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @Autowired
    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @PostMapping("/createDescription")
    public ResponseEntity<DescriptionDTO> createDescription(@Validated
                                                            @RequestBody DescriptionDTO descriptionDTO) {
        DescriptionDTO createdDescription = descriptionService.createDescription(descriptionDTO);
        return new ResponseEntity<>(createdDescription, HttpStatus.CREATED);
    }

    @GetMapping("/getDescription/{id}")
    public ResponseEntity<DescriptionDTO> getDescription(@PathVariable("id") Long id) {
        DescriptionDTO description = descriptionService.getDescription(id);
        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @GetMapping("/getAllDescriptions")
    public ResponseEntity<Page<DescriptionDTO>> getAllDescriptions(Pageable pagedDescriptions) {
        Page <DescriptionDTO> description = descriptionService.getAllDescriptions(pagedDescriptions);
        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @PutMapping("/updateDescription/{id}")
    public ResponseEntity<DescriptionDTO> updateDescription(@Validated
                                                            @PathVariable("id") Long id,
                                                            @RequestBody DescriptionDTO description) {

        description.setId(id);
        DescriptionDTO updatedDescription = descriptionService.updateDescription(description);
        return new ResponseEntity<>(updatedDescription, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDescription/{id}")
    public ResponseEntity<Map<String, String>> deleteDescription(@PathVariable("id") Long id) {
        descriptionService.deleteDescription(id);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "Description deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
