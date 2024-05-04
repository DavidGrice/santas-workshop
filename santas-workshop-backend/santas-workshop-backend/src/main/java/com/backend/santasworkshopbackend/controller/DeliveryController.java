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
                                                              @RequestParam(required = false) Long locationId,
                                                              @RequestParam(required = false) Long wishlistId,
                                                              @RequestParam(required = false) Long statusId,
                                                              @RequestParam(required = false) Date deliveryDate,
                                                              Pageable pagedDeliveries) {
        Page <DeliveryDTO> delivery = deliveryService.searchDeliveries(id, childId, wishlistId, locationId, statusId, deliveryDate, pagedDeliveries);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/existsByChildID_IdAndWishlistID_Id")
    public ResponseEntity<Boolean> existsByChildID_IdAndWishlistID_Id(@RequestParam Long childId, @RequestParam Long wishlistId) {
        return new ResponseEntity<>(deliveryService.existsByChildID_IdAndWishlistID_Id(childId, wishlistId), HttpStatus.OK);
    }

    @GetMapping("/existsByChildID_IdAndWishlistID_IdAndStatusID_Id")
    public ResponseEntity<Boolean> existsByChildID_IdAndWishlistID_IdAndStatusID_Id(@RequestParam Long childId, @RequestParam Long wishlistId, @RequestParam Long deliveryStatusId) {
        return new ResponseEntity<>(deliveryService.existsByChildID_IdAndWishlistID_IdAndStatusID_Id(childId, wishlistId, deliveryStatusId), HttpStatus.OK);
    }

    @GetMapping("/existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate")
    public ResponseEntity<Boolean> existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate(@RequestParam Long childId, @RequestParam Long wishlistId, @RequestParam Long deliveryStatusId, @RequestParam Date deliveryDate) {
        return new ResponseEntity<>(deliveryService.existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate(childId, wishlistId, deliveryStatusId, deliveryDate), HttpStatus.OK);
    }
    
}
