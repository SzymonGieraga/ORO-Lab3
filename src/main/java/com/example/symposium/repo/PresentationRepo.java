package com.example.symposium.repo;


import com.example.symposium.model.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepo extends
        JpaRepository<Presentation, Integer> {
}