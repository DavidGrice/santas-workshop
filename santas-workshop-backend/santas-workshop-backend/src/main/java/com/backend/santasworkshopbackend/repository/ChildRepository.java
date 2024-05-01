package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.backend.santasworkshopbackend.entity.Child;

public interface ChildRepository extends JpaRepository<Child, Long>, JpaSpecificationExecutor<Child> {
    boolean existsByIdAndIsNaughty(Long id, Long statusID);
}
