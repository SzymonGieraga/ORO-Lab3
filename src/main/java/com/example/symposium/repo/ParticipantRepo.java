package com.example.symposium.repo;


import com.example.symposium.dto.ParticipantPresentedCountDto;
import com.example.symposium.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepo
        extends JpaRepository<Participant, Integer> {

    @Query("SELECT DISTINCT pp.participant FROM PresentationParticipant pp " +
            "JOIN pp.presentation p " +
            "JOIN p.symposiumTopicPresentation stp " +
            "JOIN stp.symposium s " +
            "WHERE s.symposiumId = :symposiumId"
    )
    List<Participant> getAllParticipantsBySymposiumId(@Param("symposiumId") Integer symposiumId);

    @Query("SELECT DISTINCT p FROM Role r " +
            "JOIN r.participants p " +
            "JOIN p.presentationParticipants pp " +
            "JOIN pp.presentation pr " +
            "JOIN pr.symposiumTopicPresentation stp " +
            "JOIN stp.symposium s " +
            "WHERE s.symposiumId = :symposiumId " +
            "AND r.roleName = :role"
    )
    List<Participant> getAllParticipantsBySymposiumIdAndRole(@Param("symposiumId") Integer symposiumId,
                                                             @Param("role") String roleName);

    @Query("SELECT DISTINCT p FROM Country ct " +
            "JOIN ct.participants p " +
            "JOIN p.presentationParticipants pp " +
            "JOIN pp.presentation pr " +
            "JOIN pr.symposiumTopicPresentation stp " +
            "JOIN stp.symposium s " +
            "WHERE s.symposiumId = :symposiumId " +
            "AND ct.name = :country"
    )
    List<Participant> getAllParticipantsBySymposiumIdAndCountry(@Param("symposiumId") Integer symposiumId,
                                                                @Param("country") String countryName);

    @Query("SELECT NEW com.example.symposium.dto." +
            "ParticipantPresentedCountDto(p.presenter.firstName, p.presenter.lastName, COUNT(p.presenter)) " +
            "FROM Presentation p " +
            "GROUP BY p.presenter " +
            "ORDER BY COUNT(p.presenter) DESC ")
    Page<ParticipantPresentedCountDto> getPresenterWithMostPresentations(Pageable pageable);

}