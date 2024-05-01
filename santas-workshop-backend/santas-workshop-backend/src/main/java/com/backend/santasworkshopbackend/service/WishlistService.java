package com.backend.santasworkshopbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.WishlistDTO;

@Service
public interface WishlistService {

    WishlistDTO createWishlist(WishlistDTO wishlistDTO);
    WishlistDTO getWishlist(Long id);
    Page<WishlistDTO> getAllWishlists(Pageable pagedWishlist);
    WishlistDTO updateWishlist(WishlistDTO wishlistDTO);
    void deleteWishlist(Long id);
    Page<WishlistDTO> searchWishlists(String name, Long childID, Pageable pagedWishlist);
    boolean existsByIdAndWishlistIsNotNull(Long id, Long childID);
    
}
