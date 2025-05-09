package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventCenterRequest {
    private String name;
    private String description;
    private String streetAddress;
    private String state;
    private String country;
    private long amount;
    private MultipartFile[] medias;
}
