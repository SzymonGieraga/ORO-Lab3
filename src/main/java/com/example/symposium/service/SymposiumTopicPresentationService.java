package com.example.symposium.service;

import com.example.symposium.model.SymposiumTopicPresentation;
import com.example.symposium.repo.SymposiumTopicPresentationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymposiumTopicPresentationService {

    private final SymposiumTopicPresentationRepo symposiumTopicPresentationRepo;

    @Autowired
    public SymposiumTopicPresentationService(SymposiumTopicPresentationRepo symposiumTopicPresentationRepo) {
        this.symposiumTopicPresentationRepo = symposiumTopicPresentationRepo;
    }

    public SymposiumTopicPresentation save(SymposiumTopicPresentation symposiumTopicPresentation) {
        return symposiumTopicPresentationRepo.save(symposiumTopicPresentation);
    }

    public List<SymposiumTopicPresentation> saveAll(List<SymposiumTopicPresentation> symposiumTopicPresentations) {
        return symposiumTopicPresentationRepo.saveAll(symposiumTopicPresentations);
    }
}