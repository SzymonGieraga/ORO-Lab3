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
@Table(name = "symposiums")
public class Symposium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "symposium_id")
    private int symposiumId;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "symposium")
    private List<SymposiumTopicPresentation> topics = new ArrayList<>();
}