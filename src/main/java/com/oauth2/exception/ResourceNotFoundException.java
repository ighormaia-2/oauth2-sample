package com.oauth2.exception;

import javax.persistence.PersistenceException;

public class ResourceNotFoundException extends PersistenceException {

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
