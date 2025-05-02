package com.example.symposium.repo;

import com.example.symposium.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends
        JpaRepository <Country, Integer>{
}
