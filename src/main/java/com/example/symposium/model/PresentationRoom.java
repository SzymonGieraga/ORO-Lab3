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
@Table(name = "presentation_rooms")
public class PresentationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "presentationRoom")
    private List<Presentation> presentations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_hotel", nullable = false)
    private Hotel hotel;
}