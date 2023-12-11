package com.example.foodWebSiteDubai.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
