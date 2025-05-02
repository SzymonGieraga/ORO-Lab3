package com.example.symposium.service;

import com.example.symposium.model.Country;
import com.example.symposium.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepo countryRepo;

    @Autowired
    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public Country save(Country country) {
        return countryRepo.save(country);
    }

    public List<Country> saveAll(List<Country> countries) {
        return countryRepo.saveAll(countries);
    }
}