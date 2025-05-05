package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {

    private String token;
    private UUID userId;
}
