package com.example.symposium.repo;

import com.example.symposium.keys.PresentationParticipantKey;
import com.example.symposium.model.PresentationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationParticipantRepo
        extends JpaRepository<PresentationParticipant, PresentationParticipantKey> {
}