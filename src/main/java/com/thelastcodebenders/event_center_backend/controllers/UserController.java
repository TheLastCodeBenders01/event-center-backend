package com.thelastcodebenders.event_center_backend.controllers;

import com.thelastcodebenders.event_center_backend.models.dto.AppResponse;
import com.thelastcodebenders.event_center_backend.models.dto.UserProfileRequest;
import com.thelastcodebenders.event_center_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PutMapping
    public AppResponse updateUserDetails(@RequestBody UserProfileRequest userProfile) {
        return userService.updateUserDetails(userProfile);
    }
}
