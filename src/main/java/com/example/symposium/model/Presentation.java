package com.example.symposium.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "presentations")
@Entity
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id")
    private int presentationId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private Timestamp duration;

    @ManyToOne
    @JoinColumn(name = "FK_presentation_room", nullable = false)
    private PresentationRoom presentationRoom;

    @OneToOne(mappedBy = "presentation")
    private SymposiumTopicPresentation symposiumTopicPresentation;

    @OneToMany(mappedBy = "presentation")
    private List<PresentationParticipant> presentationParticipants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_presenter", nullable = false)
    private Participant presenter;
}