package com.example.symposium.service;

import jakarta.transaction.Transactional;
import com.example.symposium.keys.SymposiumTopicKey;
import com.example.symposium.keys.PresentationParticipantKey;
import com.example.symposium.model.*;

import com.example.symposium.dto.TopicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class TopicServiceTests {

    @Autowired
    private SymposiumService symposiumService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private PresentationParticipantService presentationParticipantService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PresentationRoomService presentationRoomService;

    @Autowired
    private SymposiumTopicPresentationService symposiumTopicPresentationService;

    @BeforeEach
    public void setUp() {
        // Utwórz i zapisz przykładowe role
        Role role1 = new Role();
        role1.setRoleName("Student");
        role1 = roleService.save(role1);

        Role role2 = new Role();
        role2.setRoleName("Naukowiec");
        role2 = roleService.save(role2);

        // Utwórz i zapisz przykładowe kraje
        Country country1 = new Country();
        country1.setName("Polska");
        country1 = countryService.save(country1);

        Country country2 = new Country();
        country2.setName("Dania");
        country2 = countryService.save(country2);

        // Utwórz i zapisz przykładowe hotele
        Hotel hotel1 = new Hotel();
        hotel1.setName("Hotel 1");
        hotel1.setAddress("Adres 1");
        hotel1 = hotelService.save(hotel1);

        Hotel hotel2 = new Hotel();
        hotel2.setName("Hotel 2");
        hotel2.setAddress("Adres 2");
        hotel2 = hotelService.save(hotel2);

        // Utwórz i zapisz przykładowe sale prezentacyjne
        PresentationRoom room1 = new PresentationRoom();
        room1.setCapacity(50);
        room1.setHotel(hotel1);
        room1 = presentationRoomService.save(room1);

        PresentationRoom room2 = new PresentationRoom();
        room2.setCapacity(100);
        room2.setHotel(hotel2);
        room2 = presentationRoomService.save(room2);

        PresentationRoom room3 = new PresentationRoom();
        room3.setCapacity(70);
        room3.setHotel(hotel1);
        room3 = presentationRoomService.save(room3);

        // Dodaj sale prezentacyjne do hoteli
        hotel2.getPresentationRooms().add(room3);
        hotel1.getPresentationRooms().add(room2);
        hotel1.getPresentationRooms().add(room1);
        hotel1 = hotelService.save(hotel1);
        hotel2 = hotelService.save(hotel2);

        // Utwórz i zapisz przykładowe tematy
        Topic topic1 = new Topic();
        topic1.setTitle("Temat 1");
        topic1 = topicService.save(topic1);

        Topic topic2 = new Topic();
        topic2.setTitle("Temat 2");
        topic2 = topicService.save(topic2);

        // Utwórz i zapisz przykładowe sympozja
        Symposium symposium1 = new Symposium();
        symposium1.setTitle("Sympozjum 1");
        symposium1 = symposiumService.save(symposium1);

        Symposium symposium2 = new Symposium();
        symposium2.setTitle("Sympozjum 2");
        symposium2 = symposiumService.save(symposium2);

        // Utwórz i zapisz przykładowych uczestników
        Participant participant1 = new Participant();
        participant1.setFirstName("Jan");
        participant1.setLastName("Kowalski");
        participant1.setCountryOfOrigin(country1);
        participant1.setRole(role1);
        participant1 = participantService.save(participant1);

        Participant participant2 = new Participant();
        participant2.setFirstName("Piotr");
        participant2.setLastName("Nowak");
        participant2.setCountryOfOrigin(country2);
        participant2.setRole(role2);
        participant2 = participantService.save(participant2);

        // Utwórz i zapisz przykładowe prezentacje
        Presentation presentation1 = new Presentation();
        presentation1.setDate(LocalDate.now().minusDays(1));
        presentation1.setStartTime(LocalTime.now().plusHours(1));
        presentation1.setDuration(new Timestamp(System.currentTimeMillis()));
        presentation1.setPresenter(participant1);
        presentation1.setPresentationRoom(room1);
        presentation1.setTitle("Prezentacja 1");
        presentation1 = presentationService.save(presentation1);
        room1.getPresentations().add(presentation1);
        room1 = presentationRoomService.save(room1);

        Presentation presentation2 = new Presentation();
        presentation2.setDate(LocalDate.now().plusDays(2));
        presentation2.setStartTime(LocalTime.now().plusHours(2));
        presentation2.setDuration(new Timestamp(System.currentTimeMillis()));
        presentation2.setPresenter(participant2);
        presentation2.setPresentationRoom(room2);
        presentation2.setTitle("Prezentacja 2");
        presentation2 = presentationService.save(presentation2);
        room2.getPresentations().add(presentation2);
        room2 = presentationRoomService.save(room2);

        Presentation presentation3 = new Presentation();
        presentation3.setDate(LocalDate.now().plusDays(3));
        presentation3.setStartTime(LocalTime.now().plusHours(3));
        presentation3.setDuration(new Timestamp(System.currentTimeMillis()));
        presentation3.setPresenter(participant2);
        presentation3.setPresentationRoom(room1);
        presentation3.setTitle("Prezentacja 3");
        presentation3 = presentationService.save(presentation3);
        room1.getPresentations().add(presentation3);
        room1 = presentationRoomService.save(room1);

        // Zapisz prezenterów
        participant1.getPresentedPresentations().add(presentation1);
        participant2.getPresentedPresentations().add(presentation2);
        participant2.getPresentedPresentations().add(presentation3);
        participant1 = participantService.save(participant1);
        participant2 = participantService.save(participant2);

        // Dodaj uczestników do prezentacji
        PresentationParticipantKey key1 = new PresentationParticipantKey();
        key1.setPresentationId(presentation2.getPresentationId());
        key1.setParticipantId(participant1.getParticipantId());
        PresentationParticipant presentationParticipant1 = new PresentationParticipant();
        presentationParticipant1.setId(key1);
        presentationParticipant1.setPresentation(presentation2);
        presentationParticipant1.setParticipant(participant1);
        presentationParticipant1 = presentationParticipantService.save(presentationParticipant1);

        PresentationParticipantKey key2 = new PresentationParticipantKey();
        key2.setPresentationId(presentation1.getPresentationId());
        key2.setParticipantId(participant2.getParticipantId());
        PresentationParticipant presentationParticipant2 = new PresentationParticipant();
        presentationParticipant2.setId(key2);
        presentationParticipant2.setPresentation(presentation1);
        presentationParticipant2.setParticipant(participant2);
        presentationParticipant2 = presentationParticipantService.save(presentationParticipant2);

        // Ustaw powiązania
        // presentationParticipant1: prezentacja2, uczestnik1
        presentation2.getPresentationParticipants().add(presentationParticipant1);
        participant1.getPresentationParticipants().add(presentationParticipant1);
        // presentationParticipant2: prezentacja1, uczestnik2
        presentation1.getPresentationParticipants().add(presentationParticipant2);
        participant2.getPresentationParticipants().add(presentationParticipant2);
        presentation1 = presentationService.save(presentation1);
        presentation2 = presentationService.save(presentation2);
        participant1 = participantService.save(participant1);
        participant2 = participantService.save(participant2);

        // Utwórz przykładowe dane symposium_topic_presentations
        SymposiumTopicKey symposiumTopicKey1 = new SymposiumTopicKey(symposium1.getSymposiumId(), topic1.getTopicId());
        SymposiumTopicPresentation symposiumTopicPresentation1 = new SymposiumTopicPresentation();
        symposiumTopicPresentation1.setId(symposiumTopicKey1);
        symposiumTopicPresentation1.setSymposium(symposium1);
        symposiumTopicPresentation1.setTopic(topic1);
        symposiumTopicPresentation1.setPresentation(presentation1);
        symposiumTopicPresentation1 = symposiumTopicPresentationService.save(symposiumTopicPresentation1);

        SymposiumTopicKey symposiumTopicPresentation2TopicKey2 = new SymposiumTopicKey(symposium2.getSymposiumId(), topic2.getTopicId());
        SymposiumTopicPresentation symposiumTopicPresentation2 = new SymposiumTopicPresentation();
        symposiumTopicPresentation2.setId(symposiumTopicPresentation2TopicKey2);
        symposiumTopicPresentation2.setSymposium(symposium2);
        symposiumTopicPresentation2.setTopic(topic2);
        symposiumTopicPresentation2.setPresentation(presentation2);
        symposiumTopicPresentation2 = symposiumTopicPresentationService.save(symposiumTopicPresentation2);

        // Utwórz powiązania
        // 1.) symposium1, temat1, prezentacja1
        symposium1.getTopics().add(symposiumTopicPresentation1);
        presentation1.setSymposiumTopicPresentation(symposiumTopicPresentation1);
        topic1.getSymposiums().add(symposiumTopicPresentation1);
        // 2.) symposium2, temat2, prezentacja2
        symposium2.getTopics().add(symposiumTopicPresentation2);
        presentation2.setSymposiumTopicPresentation(symposiumTopicPresentation2);
        topic2.getSymposiums().add(symposiumTopicPresentation2);
        symposiumService.saveAll(Arrays.asList(symposium1, symposium2));
        topicService.saveAll(Arrays.asList(topic1, topic2));
        presentationService.saveAll(Arrays.asList(presentation1, presentation2));
    }

    // Testuje, czy usługa zwraca każdy temat, który pojawił się w co najmniej jednej prezentacji
    @Test
    public void GetAllPresentationsTopics() {
        List<TopicDto> topics = topicService.getPresentationsTopicsBySymposiumId(1);
        topics.forEach(System.out::println);
        Assertions.assertEquals("Temat 1", topics.get(0).title());

        topics = topicService.getPresentationsTopicsBySymposiumId(2);
        topics.forEach(System.out::println);
        Assertions.assertEquals("Temat 2", topics.get(0).title());
    }
}
