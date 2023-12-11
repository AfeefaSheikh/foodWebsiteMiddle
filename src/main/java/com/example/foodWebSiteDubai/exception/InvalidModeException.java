package com.example.foodWebSiteDubai.exception;

import lombok.Data;


@Data
public class InvalidModeException extends RuntimeException {
    private String message;

    public InvalidModeException(final String errorMessage) {
        super(errorMessage);
    }
}
