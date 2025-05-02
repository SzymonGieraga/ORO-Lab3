package com.example.symposium.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.symposium.keys.SymposiumTopicKey;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "symposium_topic_presentations")
public class SymposiumTopicPresentation {

    @EmbeddedId
    private SymposiumTopicKey id;

    @ManyToOne
    @MapsId("symposiumId")
    @JoinColumn(name = "FK_symposium", nullable = false)
    private Symposium symposium;

    @ManyToOne
    @MapsId("topicId")
    @JoinColumn(name = "FK_topic", nullable = false)
    private Topic topic;

    @OneToOne
    @JoinColumn(name = "FK_presentation", nullable = false, unique = true)
    private Presentation presentation;
}