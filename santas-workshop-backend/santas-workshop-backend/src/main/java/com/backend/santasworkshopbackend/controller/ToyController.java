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

import com.backend.santasworkshopbackend.dto.ToyDTO;
import com.backend.santasworkshopbackend.service.ToyService;

@RestController
@RequestMapping("/api/toys")
public class ToyController {

    private final ToyService toyService;

    @Autowired
    public ToyController(ToyService toyService) {
        this.toyService = toyService;
    }

    @PostMapping("/createToy")
    public ResponseEntity<ToyDTO> createToy(@Validated
                                            @RequestBody ToyDTO toyDTO) {
        ToyDTO createdToy = toyService.createToy(toyDTO);
        return new ResponseEntity<>(createdToy, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToyDTO> getToy(@PathVariable("id") Long id) {
        ToyDTO toy = toyService.getToy(id);
        return new ResponseEntity<>(toy, HttpStatus.OK);
    }

    @GetMapping("/getAllToys")
    public ResponseEntity<Page<ToyDTO>> getAllToys(Pageable pagedToys) {
        Page <ToyDTO> toy = toyService.getAllToys(pagedToys);
        return new ResponseEntity<>(toy, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToyDTO> updateToy(@Validated
                                            @PathVariable("id") Long id,
                                            @RequestBody ToyDTO toy) {

        toy.setId(id);
        ToyDTO updatedToy = toyService.updateToy(toy);
        return new ResponseEntity<>(updatedToy, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteToy(@PathVariable("id") Long id) {
        toyService.deleteToy(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Toy has been deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}