package com.example.foodWebSiteDubai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String addressType;
    private String street;
    private String apartment;
    private String city;
    private String pinCode;
    private String country;
    private String landmark;
    private String phoneNumber;
    private String altPhoneNumber;

}
