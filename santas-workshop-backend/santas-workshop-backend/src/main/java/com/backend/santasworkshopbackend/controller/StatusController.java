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

import com.backend.santasworkshopbackend.dto.StatusDTO;
import com.backend.santasworkshopbackend.service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/createStatus")
    public ResponseEntity<StatusDTO> createStatus(@Validated
                                                  @RequestBody StatusDTO statusDTO) {
        StatusDTO createdStatus = statusService.createStatus(statusDTO);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping("/getStatus/{id}")
    public ResponseEntity<StatusDTO> getStatus(@PathVariable("id") Long id) {
        StatusDTO status = statusService.getStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/getAllStatuses")
    public ResponseEntity<Page<StatusDTO>> getAllStatuses(Pageable pagedStatuses) {
        Page <StatusDTO> status = statusService.getAllStatuses(pagedStatuses);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<StatusDTO> updateStatus(@Validated
                                                  @PathVariable("id") Long id,
                                                  @RequestBody StatusDTO status) {

        status.setId(id);
        StatusDTO updatedStatus = statusService.updateStatus(status);
        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStatus/{id}")
    public ResponseEntity<Map<String, String>> deleteStatus(@PathVariable("id") Long id) {
        statusService.deleteStatus(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Deleted status successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
