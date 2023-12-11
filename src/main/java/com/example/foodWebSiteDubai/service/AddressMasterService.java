package com.example.foodWebSiteDubai.service;


import com.example.foodWebSiteDubai.dto.request.AddressDto;
import com.example.foodWebSiteDubai.dto.request.AddressListDto;

public interface AddressMasterService {
    public String addAddress(AddressDto addressDto, Long userId);
    AddressListDto getAddressByUserId(Long userId);
}
