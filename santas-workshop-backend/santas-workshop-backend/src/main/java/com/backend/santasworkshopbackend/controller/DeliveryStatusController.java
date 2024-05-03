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

import com.backend.santasworkshopbackend.dto.DeliveryStatusDTO;
import com.backend.santasworkshopbackend.service.DeliveryStatusService;

@RestController
@RequestMapping("/api/delivery_status")
public class DeliveryStatusController {
    
    private final DeliveryStatusService statusService;

    @Autowired
    public DeliveryStatusController(DeliveryStatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/createStatus")
    public ResponseEntity<DeliveryStatusDTO> createStatus(@Validated
                                                  @RequestBody DeliveryStatusDTO statusDTO) {
        DeliveryStatusDTO createdStatus = statusService.createStatus(statusDTO);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping("/getStatus/{id}")
    public ResponseEntity<DeliveryStatusDTO> getStatus(@PathVariable("id") Long id) {
        DeliveryStatusDTO status = statusService.getStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/getAllStatuses")
    public ResponseEntity<Page<DeliveryStatusDTO>> getAllStatuses(Pageable pagedStatuses) {
        Page <DeliveryStatusDTO> status = statusService.getAllStatuses(pagedStatuses);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<DeliveryStatusDTO> updateStatus(@Validated
                                                  @PathVariable("id") Long id,
                                                  @RequestBody DeliveryStatusDTO status) {

        status.setId(id);
        DeliveryStatusDTO updatedStatus = statusService.updateStatus(status);
        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStatus/{id}")
    public ResponseEntity<Map<String, String>> deleteStatus(@PathVariable("id") Long id) {
        statusService.deleteStatus(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Deleted status successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchStatuses")
    public ResponseEntity<Page<DeliveryStatusDTO>> searchStatuses(@RequestParam(required = false) Long id,
                                                                  @RequestParam(required = false) String statusName,
                                                                  @RequestParam(required = false) String statusDescription,
                                                                  Pageable pagedStatuses) {
        Page <DeliveryStatusDTO> status = statusService.searchStatuses(id, statusName, statusDescription, pagedStatuses);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/existsByStatusName/{statusName}")
    public ResponseEntity<Boolean> existsByStatusName(@PathVariable("statusName") String statusName) {
        return new ResponseEntity<>(statusService.existsByStatusName(statusName), HttpStatus.OK);
    }

}
