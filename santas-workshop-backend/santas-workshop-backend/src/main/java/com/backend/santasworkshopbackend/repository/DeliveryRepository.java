package com.backend.santasworkshopbackend.repository;

import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {
    boolean existsByChildID_IdAndWishlistID_Id(Long childId, Long wishlistId);
    boolean existsByChildID_IdAndWishlistID_IdAndStatusID_Id(Long childId, Long wishlistId, Long deliveryStatusId);
    boolean existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate(Long childId, Long wishlistId, Long deliveryStatusId, Date deliveredDate);
}