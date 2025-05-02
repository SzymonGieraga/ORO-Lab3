package com.example.symposium.repo;

import com.example.symposium.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends
    JpaRepository<Hotel, Integer> {
}
