package com.example.symposium.repo;

import com.example.symposium.keys.SymposiumTopicKey;
import com.example.symposium.model.SymposiumTopicPresentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymposiumTopicPresentationRepo extends
    JpaRepository<SymposiumTopicPresentation, SymposiumTopicKey>{

}
