package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEventCenterRequest {
    private Instant bookingDate;
    private String message;
}
