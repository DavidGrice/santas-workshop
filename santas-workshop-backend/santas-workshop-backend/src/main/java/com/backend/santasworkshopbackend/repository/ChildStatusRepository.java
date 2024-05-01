package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.ChildStatus;

public interface ChildStatusRepository extends JpaRepository<ChildStatus, Long>, JpaSpecificationExecutor<ChildStatus> {
    boolean existsByChildIdAndStatusName(Long childId, String statusName);
    boolean existsByChildIdAndStatusNameAndStatusDescription(Long childId, String statusName, String statusDescription);
}
