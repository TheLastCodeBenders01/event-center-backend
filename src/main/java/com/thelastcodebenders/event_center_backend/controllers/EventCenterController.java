package com.thelastcodebenders.event_center_backend.controllers;

import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import com.thelastcodebenders.event_center_backend.models.dto.AppResponse;
import com.thelastcodebenders.event_center_backend.models.dto.BookEventCenterRequest;
import com.thelastcodebenders.event_center_backend.models.dto.EventCenterRequest;
import com.thelastcodebenders.event_center_backend.services.VendorEventCenterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "upload event center (authenticated)", description = "The medias are not string they're actually supposed to be a list of files. While the address should be of the form {\"streetAddress\":\"Adegbaju\",\"country\":\"Nigeria\",\"state\":\"Lagos\"}")
    @PostMapping
    public VendorEventCenter uploadEventCenter(@ModelAttribute EventCenterRequest request) {
        return vendorEventCenterService.uploadEventCenter(request);
    }

    @Operation(summary = "get event center by id")
    @GetMapping("{eventCenterId}")
    public VendorEventCenter getEventCenterById(@PathVariable UUID eventCenterId) {
        return vendorEventCenterService.getEventCenterById(eventCenterId);
    }

    @Operation(summary = "(paginated endpoint) get all event centers", description = "the parameters are actually query parameters")
    @GetMapping
    public List<VendorEventCenter> getAllEventCenters(
            @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber
            ) {
        return vendorEventCenterService.findAllEventCenters(pageSize, pageNumber);
    }

    @Operation(summary = "(paginated endpoint) get all event centers", description = "the parameters are actually query parameters")
    @GetMapping("search")
    public List<VendorEventCenter> searchEventCenters(
            @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("key") String key
    ) {
        return vendorEventCenterService.searchEventCenter(pageSize, pageNumber, key);
    }

    @Operation(summary = "book event center(authenticated)", description = "endpoint for booking event center")
    @PostMapping("book/{eventCenterId}")
    public AppResponse bookEventCenter(@PathVariable UUID eventCenterId, @RequestBody BookEventCenterRequest request) {
        return vendorEventCenterService.bookEventCenter(eventCenterId, request);
    }

    @Operation(summary = "get event centers uploaded by vendor (authenticated)")
    @GetMapping("me")
    public List<VendorEventCenter> getVendorEventCenters() {
        return vendorEventCenterService.getVendorEventCenters();
    }
}
