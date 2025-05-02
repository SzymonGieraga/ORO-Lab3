package com.example.symposium.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class SymposiumTopicKey implements Serializable {

    private int symposiumId;
    private int topicId;
}