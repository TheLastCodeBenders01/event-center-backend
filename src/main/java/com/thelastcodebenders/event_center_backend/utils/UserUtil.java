package com.thelastcodebenders.event_center_backend.utils;

import com.thelastcodebenders.event_center_backend.exceptions.EventCenterException;
import com.thelastcodebenders.event_center_backend.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static User getLoggedInUser() throws EventCenterException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();
            return ((User) principal);
        }
        else {
            throw new EventCenterException("Error getting logged in user");
        }
    }
}
