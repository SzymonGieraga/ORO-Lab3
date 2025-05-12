package com.example.symposium.service;

import com.example.symposium.dto.TopicDto;
import com.example.symposium.model.Topic;
import com.example.symposium.repo.TopicRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepo topicRepo;

    public TopicService(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }

    public Topic save(Topic topic) {
        return topicRepo.save(topic);
    }

    public List<Topic> saveAll(List<Topic> topics) {
        return topicRepo.saveAll(topics);
    }

    public List<TopicDto> getPresentationsTopicsBySymposiumId(Integer symposiumId) {
        return topicRepo.getPresentationsTopicsBySymposiumId(symposiumId)
                .stream()
                .map(t -> new TopicDto(t.getTitle()))
                .collect(Collectors.toList()
                );
    }
}