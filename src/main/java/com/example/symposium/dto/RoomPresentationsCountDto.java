package com.example.symposium.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomPresentationsCountDto {

    private int roomId;

    private Long count;
}