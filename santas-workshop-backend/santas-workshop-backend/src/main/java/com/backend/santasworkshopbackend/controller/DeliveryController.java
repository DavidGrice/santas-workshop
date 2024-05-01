package com.backend.santasworkshopbackend.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.backend.santasworkshopbackend.dto.DeliveryDTO;
import com.backend.santasworkshopbackend.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/createDelivery")
    public ResponseEntity<DeliveryDTO> createDelivery(@Validated
                                                      @RequestBody DeliveryDTO deliveryDTO) {
        DeliveryDTO createdDelivery = deliveryService.createDelivery(deliveryDTO);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping("/getDelivery/{id}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable("id") Long id) {
        DeliveryDTO delivery = deliveryService.getDelivery(id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/getAllDeliveries")
    public ResponseEntity<Page<DeliveryDTO>> getAllDeliveries(Pageable pagedDeliveries) {
        Page <DeliveryDTO> delivery = deliveryService.getAllDeliveries(pagedDeliveries);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @PutMapping("/updateDelivery/{id}")
    public ResponseEntity<DeliveryDTO> updateDelivery(@Validated
                                                      @PathVariable("id") Long id,
                                                      @RequestBody DeliveryDTO delivery) {
        
        delivery.setId(id);
        DeliveryDTO updatedDelivery = deliveryService.updateDelivery(delivery);
        return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDelivery/{id}")
    public ResponseEntity <Map<String, String>> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        Map<String, String> response = Map.of("message", "Delivery deleted successfully");
        response.put("message", "Delivery deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchDeliveries")
    public ResponseEntity<Page<DeliveryDTO>> searchDeliveries(@RequestParam(required = false) Long id,
                                                              @RequestParam(required = false) Long childId,
                                                              @RequestParam(required = false) Long toyId,
                                                              @RequestParam(required = false) Long deliveryStatusId,
                                                              @RequestParam(required = false) Date deliveryDate,
                                                              Pageable pagedDeliveries) {
        Page <DeliveryDTO> delivery = deliveryService.searchDeliveries(id, childId, toyId, deliveryStatusId, deliveryDate, pagedDeliveries);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/existsByDelivery")
    public ResponseEntity<Boolean> existsByChildIdAndToyId(@RequestParam Long childId, @RequestParam Long toyId) {
        return new ResponseEntity<>(deliveryService.existsByChildIdAndToyId(childId, toyId), HttpStatus.OK);
    }

    @GetMapping("/existsByDeliveryStatus")
    public ResponseEntity<Boolean> existsByChildIdAndToyIdAndDeliveryStatusId(@RequestParam Long childId, @RequestParam Long toyId, @RequestParam Long deliveryStatusId) {
        return new ResponseEntity<>(deliveryService.existsByChildIdAndToyIdAndDeliveryStatusId(childId, toyId, deliveryStatusId), HttpStatus.OK);
    }

    @GetMapping("/existsByDeliveryDate")
    public ResponseEntity<Boolean> existsByChildIdAndToyIdAndDeliveryStatusIdAndDeliveryDate(@RequestParam Long childId, @RequestParam Long toyId, @RequestParam Long deliveryStatusId, @RequestParam Date deliveryDate) {
        return new ResponseEntity<>(deliveryService.existsByChildIdAndToyIdAndDeliveryStatusIdAndDeliveryDate(childId, toyId, deliveryStatusId, deliveryDate), HttpStatus.OK);
    }
    
}
