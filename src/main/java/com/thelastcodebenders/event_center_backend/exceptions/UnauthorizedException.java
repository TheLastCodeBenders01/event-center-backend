package com.thelastcodebenders.event_center_backend.exceptions;

public class UnauthorizedException extends EventCenterException {
    public UnauthorizedException(String message) {
        super("Unable to access this resource due to " + message);
    }
}
