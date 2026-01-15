package com.vtrip.booking.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException forEntity(String entityName, Long id) {
        return new NotFoundException(entityName + " not found with id: " + id);
    }
}
