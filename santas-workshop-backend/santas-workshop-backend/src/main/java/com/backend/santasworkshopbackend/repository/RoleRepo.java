package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
