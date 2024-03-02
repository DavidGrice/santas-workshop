package com.backend.santasworkshopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.santasworkshopbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}