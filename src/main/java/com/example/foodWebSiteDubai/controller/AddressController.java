package com.example.foodWebSiteDubai.controller;

import com.example.foodWebSiteDubai.dto.request.AddressDto;
import com.example.foodWebSiteDubai.dto.request.AddressListDto;
import com.example.foodWebSiteDubai.service.AddressMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    AddressMasterService addressMasterService;

    @PostMapping("/AddAddress/{userId}")
    public String addAddressToUser(@RequestBody AddressDto addressDto, @PathVariable Long userId){
       return addressMasterService.addAddress(addressDto,userId);
    }
    @GetMapping("/getAddressByUserId/{userId}")
    public AddressListDto getAddressByUSerId(@PathVariable Long userId){
        return addressMasterService.getAddressByUserId(userId);
    }
}
