package com.example.symposium.service;

import com.example.symposium.model.Symposium;
import com.example.symposium.repo.SymposiumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymposiumService {

    private final SymposiumRepo symposiumRepo;

    private final TopicService topicService;

    @Autowired
    public SymposiumService(SymposiumRepo symposiumRepo, TopicService topicService) {
        this.symposiumRepo = symposiumRepo;
        this.topicService = topicService;
    }

    public Symposium save(Symposium symposium) {
        return symposiumRepo.save(symposium);
    }

    public List<Symposium> saveAll(List<Symposium> symposia) {
        return symposiumRepo.saveAll(symposia);
    }

}