package com.ecommerce.monolith.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {

        super(message, HttpStatus.NOT_FOUND);
    }

    public static ResourceNotFoundException create(String entityName, Object id) {
        return new ResourceNotFoundException(entityName + " not found with id " + id);
    }
}
