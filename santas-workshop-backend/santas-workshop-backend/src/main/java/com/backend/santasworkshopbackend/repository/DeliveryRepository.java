package com.backend.santasworkshopbackend.repository;

import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {
    boolean existsByChildIdAndToyId(Long childId, Long toyId);
    boolean existsByChildIdAndToyIdAndDeliveryStatusId(Long childId, Long toyId, Long deliveryStatusId);
    boolean existsByChildIdAndToyIdAndDeliveryStatusIdAndDeliveryDate(Long childId, Long toyId, Long deliveryStatusId, Date deliveredDate);
}
