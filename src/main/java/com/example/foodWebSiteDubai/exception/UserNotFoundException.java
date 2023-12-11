package com.example.foodWebSiteDubai.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
