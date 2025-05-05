package com.thelastcodebenders.event_center_backend.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("USER");
    }
}
