package com.thelastcodebenders.event_center_backend.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileRequest {
    private String phoneNumber;
    @JsonProperty("is_vendor")
    private boolean isVendor;
}
