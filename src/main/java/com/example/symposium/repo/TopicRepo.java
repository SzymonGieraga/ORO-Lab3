package com.example.symposium.repo;

import com.example.symposium.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepo extends
        JpaRepository<Topic, Integer> {

    @Query("SELECT DISTINCT t FROM Topic t " +
            "JOIN t.symposiums s " +
            "JOIN s.presentation p " +
            "WHERE s.id.symposiumId = :symposiumId"
    )
    List<Topic> getPresentationsTopicsBySymposiumId(@Param("symposiumId") Integer symposiumId);
}