package com.thelastcodebenders.event_center_backend.exceptions;

public class EventCenterNotFoundException extends EntityNotFoundException {
    public EventCenterNotFoundException() {
        super("Event Center");
    }
}
