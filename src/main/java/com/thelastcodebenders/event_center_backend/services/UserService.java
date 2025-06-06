package com.thelastcodebenders.event_center_backend.services;

import com.thelastcodebenders.event_center_backend.exceptions.UserNotFoundException;
import com.thelastcodebenders.event_center_backend.models.User;
import com.thelastcodebenders.event_center_backend.models.dto.AppResponse;
import com.thelastcodebenders.event_center_backend.models.dto.UserProfileRequest;
import com.thelastcodebenders.event_center_backend.models.dto.UserResponse;
import com.thelastcodebenders.event_center_backend.repositories.UserRepository;
import com.thelastcodebenders.event_center_backend.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(UUID userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public AppResponse updateUserDetails(UserProfileRequest userProfile) {
        User user = UserUtil.getLoggedInUser();

        if (!(userProfile.getPhoneNumber() == null) || !userProfile.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(userProfile.getPhoneNumber());
            user.setVendor(userProfile.isVendor());
        }

        saveUser(user);

        return AppResponse.builder()
                .message("User Profile Successfully updated")
                .status(HttpStatus.OK)
                .build();
    }

    public UserResponse getLoggedInUser() {
        User user = UserUtil.getLoggedInUser();

        return UserResponse.builder()
                .isVendor(user.isVendor())
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public UserResponse getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new).toDto();
    }
}
