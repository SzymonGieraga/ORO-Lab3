package com.example.symposium.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private int participantId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "FK_country", nullable = false)
    private Country countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "FK_role")
    private Role role;

    @OneToMany(mappedBy = "participant")
    private List<PresentationParticipant> presentationParticipants = new ArrayList<>();

    @OneToMany(mappedBy = "presenter")
    private List<Presentation> presentedPresentations = new ArrayList<>();
}