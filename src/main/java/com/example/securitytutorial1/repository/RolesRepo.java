package com.example.securitytutorial1.repository;

import com.example.securitytutorial1.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepo extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);

}
