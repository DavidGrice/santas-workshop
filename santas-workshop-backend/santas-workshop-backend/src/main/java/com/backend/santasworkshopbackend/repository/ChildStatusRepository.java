package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.ChildStatus;

public interface ChildStatusRepository extends JpaRepository<ChildStatus, Long>, JpaSpecificationExecutor<ChildStatus> {
    boolean existsByIdAndStatusName(Long id, String statusName);
    boolean existsByIdAndStatusNameAndStatusDescription(Long id, String statusName, String statusDescription);
}
