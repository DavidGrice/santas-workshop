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

import com.backend.santasworkshopbackend.dto.ChildDTO;
import com.backend.santasworkshopbackend.service.ChildService;

@RestController
@RequestMapping("/api/child")
public class ChildController {

    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/createChild")
    public ResponseEntity<ChildDTO> createChild(@Validated
                                                @RequestBody ChildDTO childDTO) {
        ChildDTO createdChild = childService.createChild(childDTO);
        return new ResponseEntity<>(createdChild, HttpStatus.CREATED);
    }

    @GetMapping("/getChild/{id}")
    public ResponseEntity<ChildDTO> getChild(@PathVariable("id") Long id) {
        ChildDTO child = childService.getChild(id);
        return new ResponseEntity<>(child, HttpStatus.OK);
    }

    @GetMapping("/getAllChildren")
    public ResponseEntity<Page<ChildDTO>> getAllChildren(Pageable pagedChildren) {
        Page <ChildDTO> child = childService.getAllChildren(pagedChildren);
        return new ResponseEntity<>(child, HttpStatus.OK);
    }

    @PutMapping("/updateChild/{id}")
    public ResponseEntity<ChildDTO> updateChild(@Validated
                                                @PathVariable("id") Long id,
                                                @RequestBody ChildDTO child) {

        child.setId(id);
        ChildDTO updatedChild = childService.updateChild(child);
        return new ResponseEntity<>(updatedChild, HttpStatus.OK);
    }

    @DeleteMapping("/deleteChild/{id}")
    public ResponseEntity<Map<String, String>> deleteChild(@PathVariable("id") Long id) {
        childService.deleteChild(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Child deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
