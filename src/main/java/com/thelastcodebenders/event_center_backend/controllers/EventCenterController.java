package com.thelastcodebenders.event_center_backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import com.thelastcodebenders.event_center_backend.models.dto.EventCenterRequest;
import com.thelastcodebenders.event_center_backend.services.VendorEventCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("event-centers")
public class EventCenterController {

    private final VendorEventCenterService vendorEventCenterService;

    @PostMapping
    public VendorEventCenter uploadEventCenter(@ModelAttribute EventCenterRequest request) throws JsonProcessingException {
        return vendorEventCenterService.uploadEventCenter(request);
    }

    @GetMapping("{eventCenterId}")
    public VendorEventCenter getEventCenterById(@PathVariable UUID eventCenterId) {
        return vendorEventCenterService.getEventCenterById(eventCenterId);
    }

    @GetMapping
    public List<VendorEventCenter> getAllEventCenters(
            @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber
            ) {
        return vendorEventCenterService.findAllEventCenters(pageSize, pageNumber);
    }
}
