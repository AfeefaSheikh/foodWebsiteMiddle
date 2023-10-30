package com.example.foodWebSiteDubai.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ApiResponse implements Serializable {


    private int status;
    private String message;
    private transient Object result;


    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int status,  Object result) {
        this.status = status;
        this.result = result;
    }

}





