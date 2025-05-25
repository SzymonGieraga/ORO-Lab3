package com.example.symposium;

import com.example.symposium.dto.TopicDto;
import com.example.symposium.service.*;
import jakarta.transaction.Transactional;
import com.example.symposium.keys.SymposiumTopicKey;
import com.example.symposium.keys.PresentationParticipantKey;
import com.example.symposium.dto.ParticipantCountryDto;
import com.example.symposium.dto.ParticipantPresentedCountDto;
import com.example.symposium.dto.ParticipantRoleDto;
import com.example.symposium.model.*;
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
public class SymposiumApplicationTests {

    @Autowired private SymposiumService symposiumService;
    @Autowired private TopicService topicService;
    @Autowired private PresentationService presentationService;
    @Autowired private ParticipantService participantService;
    @Autowired private PresentationParticipantService presentationParticipantService;
    @Autowired private RoleService roleService;
    @Autowired private CountryService countryService;
    @Autowired private HotelService hotelService;
    @Autowired private PresentationRoomService presentationRoomService;
    @Autowired private SymposiumTopicPresentationService symposiumTopicPresentationService;

    private int symposium1Id;
    private int symposium2Id;

    @BeforeEach
    public void setUp() {
        // --- Krok 1: Tworzenie podstawowych encji ---
        Role roleStudent = createAndSaveRole("Student");
        Role roleScientist = createAndSaveRole("Naukowiec");

        Country countryPoland = createAndSaveCountry("Polska");
        Country countryDenmark = createAndSaveCountry("Dania");

        Hotel hotel1 = createAndSaveHotel("Hotel 1", "Adres 1");
        Hotel hotel2 = createAndSaveHotel("Hotel 2", "Adres 2");

        PresentationRoom room1 = createAndSavePresentationRoom(50, hotel1);
        PresentationRoom room2 = createAndSavePresentationRoom(100, hotel2);
        PresentationRoom room3 = createAndSavePresentationRoom(70, hotel1); // Dodatkowa sala

        Topic topic1 = createAndSaveTopic("Temat 1");
        Topic topic2 = createAndSaveTopic("Temat 2");

        Symposium symposium1 = createAndSaveSymposium("Symposium 1");
        this.symposium1Id = symposium1.getSymposiumId();
        Symposium symposium2 = createAndSaveSymposium("Symposium 2");
        this.symposium2Id = symposium2.getSymposiumId();

        // --- Krok 2: Tworzenie uczestników ---
        Participant participantJan = createAndSaveParticipant("Jan", "Kowalski", countryPoland, roleStudent);
        Participant participantPiotr = createAndSaveParticipant("Piotr", "Nowak", countryDenmark, roleScientist);

        // --- Krok 3: Tworzenie prezentacji i przypisywanie prelegentów ---
        Presentation presentation1 = createAndSavePresentation("prezentacja 1", LocalDate.now().minusDays(1), participantJan, room1);
        Presentation presentation2 = createAndSavePresentation("prezentacja 2", LocalDate.now().plusDays(2), participantPiotr, room2);
        Presentation presentation3 = createAndSavePresentation("prezentacja 3", LocalDate.now().plusDays(3), participantPiotr, room1);

        // --- Krok 4: Przypisanie uczestników do prezentacji (jako słuchaczy) ---
        linkParticipantToPresentation(participantJan, presentation2);
        linkParticipantToPresentation(participantPiotr, presentation1);

        // --- Krok 5: Powiązanie sympozjów, tematów i prezentacji ---
        linkTopicToSymposiumAndPresentation(symposium1, topic1, presentation1);
        linkTopicToSymposiumAndPresentation(symposium2, topic2, presentation2);
    }

    // Testuje czy serwis zwraca uczestników, którzy brali udział w konferencji o danym id
    @Test
    public void GetAllParticipantsByConferenceId() {
        List<ParticipantRoleDto> participants = participantService.getAllParticipantsBySymposiumId(symposium1Id);
        Assertions.assertEquals("Piotr", participants.get(0).firstName());
        Assertions.assertEquals("Nowak", participants.get(0).lastName());
        participants.forEach(System.out::println);
        participants = participantService.getAllParticipantsBySymposiumId(symposium2Id);
        Assertions.assertEquals("Jan", participants.get(0).firstName());
        Assertions.assertEquals("Kowalski", participants.get(0).lastName());
        participants.forEach(System.out::println);
    }

    // Testuje czy serwis zwraca uczestników o danej roli, którzy wzięli udział w podanym sympozjum
    @Test
    public void GetAllParticipantsBySymposiumIdAndParticipantRole() {
        List<ParticipantRoleDto> participants = participantService.getAllParticipantsBySymposiumIdAndRole(symposium1Id, "Naukowiec");
        Assertions.assertEquals("Piotr", participants.get(0).firstName());
        Assertions.assertEquals("Nowak", participants.get(0).lastName());
        participants.forEach(System.out::println);

        participants = participantService.getAllParticipantsBySymposiumIdAndRole(symposium1Id, "Student");
        Assertions.assertEquals(0, participants.size());

        participants = participantService.getAllParticipantsBySymposiumIdAndRole(symposium2Id, "Student");
        Assertions.assertEquals("Jan", participants.get(0).firstName());
        Assertions.assertEquals("Kowalski", participants.get(0).lastName());
        participants.forEach(System.out::println);

        participants = participantService.getAllParticipantsBySymposiumIdAndRole(symposium2Id, "Naukowiec");
        Assertions.assertEquals(0, participants.size());
    }

    // Testuje czy serwis zwraca uczestników z danego kraju, którzy wzięli udział w sympozjum o podanym id
    @Test
    public void GetAllPresentationsByTopicIdAndCountry() {
        List<ParticipantCountryDto> participants = participantService.getParticipantsBySymposiumIdAndCountry(symposium1Id, "Dania");
        Assertions.assertEquals("Piotr", participants.get(0).firstName());
        Assertions.assertEquals("Nowak", participants.get(0).lastName());
        participants.forEach(System.out::println);

        participants = participantService.getParticipantsBySymposiumIdAndCountry(symposium1Id, "Polska");
        Assertions.assertEquals(0, participants.size());

        participants = participantService.getParticipantsBySymposiumIdAndCountry(symposium2Id, "Polska");
        Assertions.assertEquals("Jan", participants.get(0).firstName());
        Assertions.assertEquals("Kowalski", participants.get(0).lastName());
        participants.forEach(System.out::println);

        participants = participantService.getParticipantsBySymposiumIdAndCountry(symposium2Id, "Dania");
        Assertions.assertEquals(0, participants.size());
    }

    // Testuje czy serwis zwraca uczestnika, który zaprezentował największą liczbę prezentacji
    @Test
    public void GetPresenterWithMostPresentations() {
        ParticipantPresentedCountDto presenter = participantService.getPresenterWithMostPresentations();
        Assertions.assertEquals(2, presenter.getCount());
        Assertions.assertEquals("Piotr", presenter.getFirstName());
        Assertions.assertEquals("Nowak", presenter.getLastName());
        System.out.println(presenter);
    }
    // Testuje, czy usługa zwraca każdy temat, który pojawił się w co najmniej jednej prezentacji
    @Test
    public void GetAllPresentationsTopics() {
        List<TopicDto> topics = topicService.getPresentationsTopicsBySymposiumId(symposium1Id);
        topics.forEach(System.out::println);
        Assertions.assertEquals("Temat 1", topics.get(0).title());

        topics = topicService.getPresentationsTopicsBySymposiumId(symposium2Id);
        topics.forEach(System.out::println);
        Assertions.assertEquals("Temat 2", topics.get(0).title());
    }

    // Testuje, czy serwis zwraca poprawną liczbę prezentacji dla każdej sali
    @Test
    public void GetNumberOfPresentationsForEachRoom() {
        var roomsPresentationCounts = presentationRoomService.getPresentationCountByRoom();
        roomsPresentationCounts.forEach(System.out::println);
        Assertions.assertEquals(2, roomsPresentationCounts.get(0).getCount()); // Room 1
        Assertions.assertEquals(1, roomsPresentationCounts.get(1).getCount()); // Room 2
        Assertions.assertEquals(0, roomsPresentationCounts.get(2).getCount()); // Room 3
    }

    private Role createAndSaveRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        return roleService.save(role);
    }

    private Country createAndSaveCountry(String name) {
        Country country = new Country();
        country.setName(name);
        return countryService.save(country);
    }

    private Hotel createAndSaveHotel(String name, String address) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        return hotelService.save(hotel);
    }

    private PresentationRoom createAndSavePresentationRoom(int capacity, Hotel hotel) {
        PresentationRoom room = new PresentationRoom();
        room.setCapacity(capacity);
        room.setHotel(hotel);
        return presentationRoomService.save(room);
    }

    private Topic createAndSaveTopic(String title) {
        Topic topic = new Topic();
        topic.setTitle(title);
        return topicService.save(topic);
    }

    private Symposium createAndSaveSymposium(String title) {
        Symposium symposium = new Symposium();
        symposium.setTitle(title);
        return symposiumService.save(symposium);
    }

    private Participant createAndSaveParticipant(String firstName, String lastName, Country country, Role role) {
        Participant participant = new Participant();
        participant.setFirstName(firstName);
        participant.setLastName(lastName);
        participant.setCountryOfOrigin(country);
        participant.setRole(role);
        return participantService.save(participant);
    }

    private Presentation createAndSavePresentation(String title, LocalDate date, Participant presenter, PresentationRoom room) {
        Presentation presentation = new Presentation();
        presentation.setTitle(title);
        presentation.setDate(date);
        presentation.setStartTime(LocalTime.now()); // Uproszczono dla zwięzłości
        presentation.setDuration(new Timestamp(System.currentTimeMillis()));
        presentation.setPresenter(presenter);
        presentation.setPresentationRoom(room);
        return presentationService.save(presentation);
    }

    private void linkParticipantToPresentation(Participant participant, Presentation presentation) {
        PresentationParticipantKey key = new PresentationParticipantKey(presentation.getPresentationId(), participant.getParticipantId());
        PresentationParticipant link = new PresentationParticipant();
        link.setId(key);
        link.setPresentation(presentation);
        link.setParticipant(participant);
        presentationParticipantService.save(link);
    }

    private void linkTopicToSymposiumAndPresentation(Symposium symposium, Topic topic, Presentation presentation) {
        SymposiumTopicKey key = new SymposiumTopicKey(symposium.getSymposiumId(), topic.getTopicId());
        SymposiumTopicPresentation link = new SymposiumTopicPresentation();
        link.setId(key);
        link.setSymposium(symposium);
        link.setTopic(topic);
        link.setPresentation(presentation);
        symposiumTopicPresentationService.save(link);
    }
}