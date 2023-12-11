package com.example.foodWebSiteDubai.exception;

import lombok.Data;


@Data
public class InvalidFilterFeildException extends RuntimeException {
    private String message;

    public InvalidFilterFeildException(final String errorMessage) {
        super(errorMessage);
    }
}
