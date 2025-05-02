package com.example.symposium.service;

import com.example.symposium.model.PresentationParticipant;
import com.example.symposium.repo.PresentationParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationParticipantService {

    private final PresentationParticipantRepo presentationParticipantRepo;

    @Autowired
    public PresentationParticipantService(PresentationParticipantRepo presentationParticipantRepo) {
        this.presentationParticipantRepo = presentationParticipantRepo;
    }

    public PresentationParticipant save(PresentationParticipant presentationParticipant) {
        return presentationParticipantRepo.save(presentationParticipant);
    }

    public List<PresentationParticipant> saveAll(List<PresentationParticipant> presentationParticipants) {
        return presentationParticipantRepo.saveAll(presentationParticipants);
    }
}