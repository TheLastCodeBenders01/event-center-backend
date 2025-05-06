package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventCenterRequest {
    private String name;
    private String description;
    private String address;
    private long amount;
    private MultipartFile[] medias;
}
