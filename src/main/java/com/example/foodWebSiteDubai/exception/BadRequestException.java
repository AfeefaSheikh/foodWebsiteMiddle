package com.example.foodWebSiteDubai.exception;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {
    private String message;

    public BadRequestException(final String errorMessage) {
        super(errorMessage);
    }
}
