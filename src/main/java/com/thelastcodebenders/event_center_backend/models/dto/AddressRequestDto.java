package com.thelastcodebenders.event_center_backend.models.dto;

import lombok.Data;

@Data
public class AddressRequestDto {

    private String streetAddress;
    private String state;
    private String country;
}
