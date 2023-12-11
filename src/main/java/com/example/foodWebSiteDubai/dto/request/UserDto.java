package com.example.foodWebSiteDubai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
}
