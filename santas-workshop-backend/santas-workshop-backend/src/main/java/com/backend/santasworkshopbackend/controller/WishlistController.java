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

import com.backend.santasworkshopbackend.dto.WishlistDTO;
import com.backend.santasworkshopbackend.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/createWishlist")
    public ResponseEntity<WishlistDTO> createWishlist(@Validated
                                                @RequestBody WishlistDTO wishlistDTO) {
        WishlistDTO createdWishlist = wishlistService.createWishlist(wishlistDTO);
        return new ResponseEntity<>(createdWishlist, HttpStatus.CREATED);
    }

    @GetMapping("/getWishlist/{id}")
    public ResponseEntity<WishlistDTO> getWishlist(@PathVariable("id") Long id) {
        WishlistDTO wishlist = wishlistService.getWishlist(id);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @GetMapping("/getAllWishlists")
    public ResponseEntity<Page<WishlistDTO>> getAllWishlists(Pageable pagedWishlists) {
        Page <WishlistDTO> wishlist = wishlistService.getAllWishlists(pagedWishlists);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @PutMapping("/updateWishlist/{id}")
    public ResponseEntity<WishlistDTO> updateWishlist(@Validated
                                                @PathVariable("id") Long id,
                                                @RequestBody WishlistDTO wishlist) {

        wishlist.setId(id);
        WishlistDTO updatedWishlist = wishlistService.updateWishlist(wishlist);
        return new ResponseEntity<>(updatedWishlist, HttpStatus.OK);
    }

    @DeleteMapping("/deleteWishlist/{id}")
    public ResponseEntity<Map<String, String>> deleteWishlist(@PathVariable("id") Long id) {
        wishlistService.deleteWishlist(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Wishlist deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
