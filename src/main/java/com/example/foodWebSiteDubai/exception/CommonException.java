package com.example.foodWebSiteDubai.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CommonException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public CommonException(final HttpStatus httpStatus, final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
        this.httpStatus = httpStatus;
    }
}

