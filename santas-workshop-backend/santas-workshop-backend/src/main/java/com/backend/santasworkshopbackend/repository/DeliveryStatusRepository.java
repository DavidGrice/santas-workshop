package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.backend.santasworkshopbackend.entity.DeliveryStatus;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long>, JpaSpecificationExecutor<DeliveryStatus> {
    boolean existsByStatusName(String statusName);
}
