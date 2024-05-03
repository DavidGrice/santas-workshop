package com.backend.santasworkshopbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backend.santasworkshopbackend.dto.WishlistDTO;
import com.backend.santasworkshopbackend.entity.Wishlist;
import com.backend.santasworkshopbackend.repository.WishlistRepository;
import com.backend.santasworkshopbackend.service.WishlistService;
import com.backend.santasworkshopbackend.specification.SearchCriteria;
import com.backend.santasworkshopbackend.specification.WishlistSpecification;

@Service
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;
    private ModelMapper modelMapper;

    private static final Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public WishlistServiceImpl(ModelMapper modelMapper, WishlistRepository wishlistRepository) {
        this.modelMapper = modelMapper;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public WishlistDTO createWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = modelMapper.map(wishlistDTO, Wishlist.class);
        Logger.info(wishlist.getChild() + " " + wishlist.getToy());
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        WishlistDTO savedWishlistDTO = modelMapper.map(savedWishlist, WishlistDTO.class);
        return savedWishlistDTO;
    }

    @Override
    public WishlistDTO getWishlist(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        WishlistDTO wishlistDTO = modelMapper.map(wishlist, WishlistDTO.class);
        return wishlistDTO;
    }

    @Override
    public Page<WishlistDTO> getAllWishlists(Pageable pagedWishlist) {
        Page<Wishlist> wishlists = wishlistRepository.findAll(pagedWishlist);
        Page<WishlistDTO> wishlistDTOs = wishlists.map(wishlist -> modelMapper.map(wishlist, WishlistDTO.class));
        return wishlistDTOs;
    }

    @Override
    public WishlistDTO updateWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = modelMapper.map(wishlistDTO, Wishlist.class);
        Wishlist updatedWishlist = wishlistRepository.save(wishlist);
        WishlistDTO updatedWishlistDTO = modelMapper.map(updatedWishlist, WishlistDTO.class);
        return updatedWishlistDTO;
    }

    @Override
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    @Override
    public Page<WishlistDTO> searchWishlists(String name, Long childID, Pageable pagedWishlist) {
        Specification<Wishlist> spec = Specification.where(null);

        if (name != null) {
            Specification<Wishlist> specName = new WishlistSpecification(new SearchCriteria("name", ":", name));
            spec = spec.and(specName);
        }
        if (childID != null) {
            Specification<Wishlist> specChildID = new WishlistSpecification(new SearchCriteria("childID", ":", childID));
            spec = spec.and(specChildID);
        }
        
        Page<Wishlist> wishlists = wishlistRepository.findAll(spec, pagedWishlist);
        return wishlists.map(wishlist -> modelMapper.map(wishlist, WishlistDTO.class));

    }

    @Override
    public boolean existsByIdAndChild_Id(Long id, Long childID) {
        return wishlistRepository.existsByIdAndChild_Id(id, childID);
    }
    
}
