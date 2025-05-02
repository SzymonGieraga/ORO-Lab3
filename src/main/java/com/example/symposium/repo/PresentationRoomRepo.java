package com.example.symposium.repo;

import com.example.symposium.dto.RoomPresentationsCountDto;
import com.example.symposium.model.PresentationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentationRoomRepo extends
        JpaRepository<PresentationRoom, Integer> {

    @Query("SELECT NEW com.example.symposium.dto.RoomPresentationsCountDto(pr.roomId, COUNT(p.presentationId)) " +
            "FROM PresentationRoom pr " +
            "LEFT JOIN pr.presentations p " +
            "GROUP BY pr.roomId " +
            "ORDER BY pr.roomId"
    )
    List<RoomPresentationsCountDto> getPresentationCountByRoom();
}