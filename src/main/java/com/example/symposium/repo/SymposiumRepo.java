package com.example.symposium.repo;

import com.example.symposium.model.Symposium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymposiumRepo extends
        JpaRepository<Symposium, Integer> {
}