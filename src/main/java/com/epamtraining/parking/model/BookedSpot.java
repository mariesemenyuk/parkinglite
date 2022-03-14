package com.epamtraining.parking.model;

import com.epamtraining.parking.domain.entity.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookedSpot {

    private String email;
    private String spotLocation;
    private LocalDateTime from;
    private LocalDateTime to;
}
