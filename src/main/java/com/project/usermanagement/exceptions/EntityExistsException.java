package com.project.usermanagement.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String message) {
        super(message);
    }

}
