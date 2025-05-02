package com.example.symposium.service;

import com.example.symposium.dto.RoomPresentationsCountDto;
import com.example.symposium.model.PresentationRoom;
import com.example.symposium.repo.PresentationRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationRoomService {

    private final PresentationRoomRepo presentationRoomRepo;

    @Autowired
    public PresentationRoomService(PresentationRoomRepo presentationRoomRepo) {
        this.presentationRoomRepo = presentationRoomRepo;
    }

    public PresentationRoom save(PresentationRoom presentationRoom) {
        return presentationRoomRepo.save(presentationRoom);
    }

    public List<PresentationRoom> saveAll(List<PresentationRoom> presentationRooms) {
        return presentationRoomRepo.saveAll(presentationRooms);
    }

    List<RoomPresentationsCountDto> getPresentationCountByRoom() {
        return presentationRoomRepo.getPresentationCountByRoom();
    }
}