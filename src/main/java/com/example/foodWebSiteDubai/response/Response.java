package com.example.foodWebSiteDubai.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    private Response() {
    }

    public static ResponseEntity<ApiResponse> getResponse(final HttpStatus statusCode, final String message) {
        return getResponseEntity(statusCode, message, null);
    }


    public static ResponseEntity<ApiResponse> getResponse(final int responseCode, final String message,
                                                          final Object result) {
        return ResponseEntity.ok().body(new ApiResponse(responseCode, message, result));
    }


    public static ResponseEntity<ApiResponse> getResponseEntity(final HttpStatus statusCode, final String message, final Object result) {
        return ResponseEntity.status(statusCode).body(new ApiResponse(statusCode.value(), message, result));
    }
    public static ResponseEntity<ApiResponse> getResponseMessage(final HttpStatus statusCode, final Object result) {
        return ResponseEntity.status(statusCode).body(new ApiResponse(statusCode.value(),result));
    }

}




