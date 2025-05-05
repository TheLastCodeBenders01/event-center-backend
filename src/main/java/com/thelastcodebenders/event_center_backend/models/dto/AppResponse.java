package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class AppResponse {

    private String message;
    private HttpStatus status;
}
