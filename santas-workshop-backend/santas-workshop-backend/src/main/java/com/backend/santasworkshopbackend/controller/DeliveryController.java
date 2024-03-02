package com.backend.santasworkshopbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return ResponseEntity.ok(deliveryService.createDelivery(deliveryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDelivery(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.getDelivery(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllDeliveries(Pageable pageable) {
        return ResponseEntity.ok(deliveryService.getAllDeliveries(pageable));
    }

    @PutMapping
    public ResponseEntity<?> updateDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return ResponseEntity.ok(deliveryService.updateDelivery(deliveryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok().build();
    }
    
}
