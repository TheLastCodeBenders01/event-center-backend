package com.thelastcodebenders.event_center_backend.exceptions;

public class EntityNotFoundException extends EventCenterException {

    EntityNotFoundException(String ENTITY) {
        super(ENTITY + " not found");
    }
}
