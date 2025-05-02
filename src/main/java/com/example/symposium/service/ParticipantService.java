package com.example.symposium.service;

import com.example.symposium.dto.ParticipantCountryDto;
import com.example.symposium.dto.ParticipantPresentedCountDto;
import com.example.symposium.dto.ParticipantRoleDto;
import com.example.symposium.model.Participant;
import com.example.symposium.repo.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepo participantRepo;

    @Autowired
    public ParticipantService(ParticipantRepo participantRepo) {
        this.participantRepo = participantRepo;
    }

    public Participant save(Participant participant) {
        return participantRepo.save(participant);
    }

    public List<Participant> saveAll(List<Participant> participants) {
        return participantRepo.saveAll(participants);
    }

    public List<ParticipantRoleDto> getAllParticipantsBySymposiumId(Integer symposiumId) {
        return participantRepo.getAllParticipantsBySymposiumId(symposiumId)
                .stream()
                .map(p -> new ParticipantRoleDto(p.getFirstName(), p.getLastName(), p.getRole().getRoleName()))
                .collect(Collectors.toList()
                );
    }

    public List<ParticipantRoleDto> getAllParticipantsBySymposiumIdAndRole(Integer symposiumId, String role) {
        return participantRepo.getAllParticipantsBySymposiumIdAndRole(symposiumId, role)
                .stream()
                .map(p -> new ParticipantRoleDto(p.getFirstName(), p.getLastName(), p.getRole().getRoleName()))
                .collect(Collectors.toList()
                );
    }

    public List<ParticipantCountryDto> getParticipantsBySymposiumIdAndCountry(Integer symposiumId, String country) {
        return participantRepo.getAllParticipantsBySymposiumIdAndCountry(symposiumId, country)
                .stream()
                .map(p -> new ParticipantCountryDto(p.getFirstName(), p.getLastName(), p.getCountryOfOrigin().getName()))
                .collect(Collectors.toList()
                );
    }

    public ParticipantPresentedCountDto getPresenterWithMostPresentations() {
        return participantRepo
                .getPresenterWithMostPresentations(PageRequest.of(0, 1))
                .getContent()
                .getFirst();
    }
}