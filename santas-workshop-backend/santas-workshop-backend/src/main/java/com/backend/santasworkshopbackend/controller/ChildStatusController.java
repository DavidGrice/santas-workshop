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

import com.backend.santasworkshopbackend.dto.ChildStatusDTO;
import com.backend.santasworkshopbackend.service.ChildStatusService;

@RestController
@RequestMapping("/api/child_status")
public class ChildStatusController {
    
    private final ChildStatusService statusService;

    @Autowired
    public ChildStatusController(ChildStatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/createStatus")
    public ResponseEntity<ChildStatusDTO> createStatus(@Validated
                                                  @RequestBody ChildStatusDTO statusDTO) {
        ChildStatusDTO createdStatus = statusService.createStatus(statusDTO);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping("/getStatus/{id}")
    public ResponseEntity<ChildStatusDTO> getStatus(@PathVariable("id") Long id) {
        ChildStatusDTO status = statusService.getStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/getAllStatuses")
    public ResponseEntity<Page<ChildStatusDTO>> getAllStatuses(Pageable pagedStatuses) {
        Page <ChildStatusDTO> status = statusService.getAllStatuses(pagedStatuses);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<ChildStatusDTO> updateStatus(@Validated
                                                  @PathVariable("id") Long id,
                                                  @RequestBody ChildStatusDTO status) {

        status.setId(id);
        ChildStatusDTO updatedStatus = statusService.updateStatus(status);
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
    public ResponseEntity<Page<ChildStatusDTO>> searchStatuses(@RequestParam(required = false) Long id,
                                                               @RequestParam(required = false) String statusName,
                                                               @RequestParam(required = false) String statusDescription,
                                                               Pageable pageable) {
        Page<ChildStatusDTO> statuses = statusService.searchStatuses(id, statusName, statusDescription, pageable);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping("/existsByIdAndStatusName/{id}/{statusName}")
    public ResponseEntity<Boolean> existsByIdAndStatusName(@PathVariable Long id, @PathVariable String statusName) {
        return new ResponseEntity<>(statusService.existsByIdAndStatusName(id, statusName), HttpStatus.OK);
    }
    
    @GetMapping("/existsByIdAndStatusNameAndStatusDescription/{id}/{statusName}/{statusDescription}")
    public ResponseEntity<Boolean> existsByIdAndStatusNameAndStatusDescription(@PathVariable Long id, @PathVariable String statusName, @PathVariable String statusDescription) {
        return new ResponseEntity<>(statusService.existsByIdAndStatusNameAndStatusDescription(id, statusName, statusDescription), HttpStatus.OK);
    }

}
