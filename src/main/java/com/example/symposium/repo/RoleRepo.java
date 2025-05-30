package com.example.symposium.repo;

import com.example.symposium.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends
        JpaRepository<Role, Integer> {
}