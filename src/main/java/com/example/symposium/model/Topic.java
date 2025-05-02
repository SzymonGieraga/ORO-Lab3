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
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int topicId;

    @Column(nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "topic")
    private List<SymposiumTopicPresentation> symposiums = new ArrayList<>();
}