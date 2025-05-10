package com.thelastcodebenders.event_center_backend.controllers;

import com.thelastcodebenders.event_center_backend.models.dto.AppResponse;
import com.thelastcodebenders.event_center_backend.models.dto.UserProfileRequest;
import com.thelastcodebenders.event_center_backend.models.dto.UserResponse;
import com.thelastcodebenders.event_center_backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "update user profile (authenticated)")
    @PutMapping
    public AppResponse updateUserDetails(@RequestBody UserProfileRequest userProfile) {
        return userService.updateUserDetails(userProfile);
    }

    @Operation(summary = "get logged in user (authenticated)")
    @GetMapping("logged-in-user")
    public UserResponse getLoggedInUser() {
        return userService.getLoggedInUser();
    }

    @Operation(summary = "get user by id")
    @GetMapping("by-id/{userId}")
    public UserResponse getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}
