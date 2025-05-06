package com.thelastcodebenders.event_center_backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thelastcodebenders.event_center_backend.adapter.FileServiceAdapter;
import com.thelastcodebenders.event_center_backend.exceptions.EventCenterNotFoundException;
import com.thelastcodebenders.event_center_backend.models.User;
import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import com.thelastcodebenders.event_center_backend.models.dto.Address;
import com.thelastcodebenders.event_center_backend.models.dto.AddressRequestDto;
import com.thelastcodebenders.event_center_backend.models.dto.EventCenterRequest;
import com.thelastcodebenders.event_center_backend.repositories.VendorEventCenterRepository;
import com.thelastcodebenders.event_center_backend.utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorEventCenterService {

    private final VendorEventCenterRepository vendorEventCenterRepository;
    private final FileServiceAdapter fileServiceAdapter;
    private final ObjectMapper objectMapper;

    @Transactional
    public VendorEventCenter uploadEventCenter(EventCenterRequest request) throws JsonProcessingException {
        User user = UserUtil.getLoggedInUser();

        AddressRequestDto address = objectMapper.readValue(request.getAddress(), AddressRequestDto.class);

        VendorEventCenter eventCenter = VendorEventCenter.builder()
                .name(request.getName())
                .address(
                        Address.builder()
                                .streetAddress(address.getStreetAddress())
                                .country(address.getCountry())
                                .state(address.getState())
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
        return vendorEventCenterRepository.findById(eventCenterId).orElseThrow(EventCenterNotFoundException::new);
    }

    public List<VendorEventCenter> findAllEventCenters(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vendorEventCenterRepository.findAll(pageable).getContent();
    }
}
