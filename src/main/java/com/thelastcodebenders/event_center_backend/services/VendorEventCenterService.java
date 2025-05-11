package com.thelastcodebenders.event_center_backend.services;

import com.thelastcodebenders.event_center_backend.adapter.FileServiceAdapter;
import com.thelastcodebenders.event_center_backend.exceptions.EventCenterNotFoundException;
import com.thelastcodebenders.event_center_backend.exceptions.UnauthorizedException;
import com.thelastcodebenders.event_center_backend.models.EventCenterBooking;
import com.thelastcodebenders.event_center_backend.models.User;
import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import com.thelastcodebenders.event_center_backend.models.dto.Address;
import com.thelastcodebenders.event_center_backend.models.dto.AppResponse;
import com.thelastcodebenders.event_center_backend.models.dto.BookEventCenterRequest;
import com.thelastcodebenders.event_center_backend.models.dto.EventCenterRequest;
import com.thelastcodebenders.event_center_backend.repositories.EventCenterBookingRepository;
import com.thelastcodebenders.event_center_backend.repositories.VendorEventCenterRepository;
import com.thelastcodebenders.event_center_backend.repositories.specifications.VendorEventCenterSpecification;
import com.thelastcodebenders.event_center_backend.utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VendorEventCenterService {

    private final VendorEventCenterRepository vendorEventCenterRepository;
    private final FileServiceAdapter fileServiceAdapter;
    private final EventCenterBookingRepository eventCenterBookingRepository;

    @Transactional
    public VendorEventCenter uploadEventCenter(EventCenterRequest request) {
        User user = UserUtil.getLoggedInUser();

        if (user.isVendor()) {
            throw new UnauthorizedException("You're not a vendor");
        }

        VendorEventCenter eventCenter = VendorEventCenter.builder()
                .name(request.getName())
                .address(
                        Address.builder()
                                .streetAddress(request.getStreetAddress())
                                .country(request.getCountry())
                                .state(request.getState())
                                .build()
                )
                .description(request.getDescription())
                .amount(request.getAmount())
                .owner(user)
                .medias(fileServiceAdapter.buildFileUri(fileServiceAdapter.uploadFiles(request.getMedias())))
                .bookings(new ArrayList<>())
                .build();

        return saveVendorEvent(eventCenter);
    }

    public VendorEventCenter saveVendorEvent(VendorEventCenter vendorEventCenter) {
        return vendorEventCenterRepository.save(vendorEventCenter);
    }

    public VendorEventCenter getEventCenterById(UUID eventCenterId) {
        return setEventCenterBookingsToDto(vendorEventCenterRepository.findById(eventCenterId).orElseThrow(EventCenterNotFoundException::new));
    }

    public List<VendorEventCenter> findAllEventCenters(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorEventCenter> eventCenters = vendorEventCenterRepository.findAll(pageable).getContent();
        return eventCenters.parallelStream().map(this::setEventCenterBookingsToDto).collect(Collectors.toList());
    }

    public AppResponse bookEventCenter(UUID eventCenterId, BookEventCenterRequest request) {
        EventCenterBooking booking = EventCenterBooking.builder()
                .bookedBy(UserUtil.getLoggedInUser())
                .bookingDate(request.getBookingDate())
                .message(request.getMessage())
                .eventCenter(getEventCenterById(eventCenterId))
                .build();

        eventCenterBookingRepository.save(booking);

        return AppResponse.builder()
                .status(HttpStatus.OK)
                .message("Event Center Booking Request Successfully Sent")
                .build();
    }

    public List<VendorEventCenter> searchEventCenter(int pageSize, int pageNumber, String key) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<VendorEventCenter> specification = VendorEventCenterSpecification.search(key);
        List<VendorEventCenter> eventCenters = vendorEventCenterRepository.findAll(specification, pageable).getContent();
        return eventCenters.parallelStream().map(this::setEventCenterBookingsToDto).collect(Collectors.toList());
    }

    public List<VendorEventCenter> getVendorEventCenters() {
        List<VendorEventCenter> eventCenters = vendorEventCenterRepository.findAllByOwner(UserUtil.getLoggedInUser());
        return eventCenters.parallelStream().map(this::setEventCenterBookingsToDto).collect(Collectors.toList());
    }

    public VendorEventCenter setEventCenterBookingsToDto(VendorEventCenter eventCenter) {
        eventCenter.setBookings(eventCenter.getBookings().parallelStream().map(EventCenterBooking::toDto).collect(Collectors.toList()));
        eventCenter.toDto();
        return eventCenter;
    }
}
