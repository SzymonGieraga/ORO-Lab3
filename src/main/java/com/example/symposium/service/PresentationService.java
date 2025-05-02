package com.example.symposium.service;

import com.example.symposium.model.Presentation;
import com.example.symposium.repo.PresentationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationService {

    private final PresentationRepo presentationRepo;

    @Autowired
    public PresentationService(PresentationRepo presentationRepo) {
        this.presentationRepo = presentationRepo;
    }

    public Presentation save(Presentation presentation) {
        return presentationRepo.save(presentation);
    }

    public List<Presentation> saveAll(List<Presentation> presentations) {
        return presentationRepo.saveAll(presentations);
    }
}