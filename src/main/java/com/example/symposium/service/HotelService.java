package com.example.symposium.service;

import com.example.symposium.model.Hotel;
import com.example.symposium.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepo hotelRepo;

    @Autowired
    public HotelService(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public Hotel save(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    public List<Hotel> saveAll(List<Hotel> hotels) {
        return hotelRepo.saveAll(hotels);
    }
}