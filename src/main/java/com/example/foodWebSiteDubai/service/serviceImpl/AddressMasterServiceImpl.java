package com.example.foodWebSiteDubai.service.serviceImpl;


import com.example.foodWebSiteDubai.dto.request.AddressDto;
import com.example.foodWebSiteDubai.dto.request.AddressListDto;
import com.example.foodWebSiteDubai.entity.AddressMaster;
import com.example.foodWebSiteDubai.entity.UserMaster;
import com.example.foodWebSiteDubai.repository.AddressMasterRepo;
import com.example.foodWebSiteDubai.repository.UserMasterRepo;
import com.example.foodWebSiteDubai.service.AddressMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AddressMasterServiceImpl implements AddressMasterService {
    @Autowired
    UserMasterRepo userMasterRepo;

    @Autowired
    AddressMasterRepo addressMasterRepo;

    @Override
    public String addAddress(AddressDto addressDto, Long userId) {
        Optional<UserMaster> userMaster = userMasterRepo.findById(userId);
       if (userMaster.isPresent()){
                UserMaster userMaster1 = userMaster.get();
               AddressMaster addressMaster = new AddressMaster();
               addressMaster.setAddressType(addressDto.getAddressType());
               addressMaster.setApartment(addressDto.getApartment());
               addressMaster.setCity(addressDto.getCity());
               addressMaster.setCountry(addressDto.getCountry());
               addressMaster.setLandmark(addressDto.getLandmark());
               addressMaster.setPhoneNumber(addressDto.getPhoneNumber());
               addressMaster.setAltPhoneNumber(addressDto.getAltPhoneNumber());
               addressMaster.setStreet(addressDto.getStreet());
               addressMaster.setPinCode(addressDto.getPinCode());
               userMaster1.setAddressMasters(Arrays.asList(addressMaster));
               userMasterRepo.save(userMaster1);
               return "Address saved";
       }
       return "User not present";
    }

    @Override
    public AddressListDto getAddressByUserId(Long userId) {
        if(addressMasterRepo.existsByUserId(userId)){
            List<AddressMaster> addressMaster1 = addressMasterRepo.findByUserId(userId);
            AddressListDto addressListDto = new AddressListDto();
            List<AddressDto> addressDtoList = new ArrayList<>();
            for(AddressMaster addressMaster : addressMaster1){
                AddressDto addressDto =new AddressDto();
                addressDto.setAddressType(addressMaster.getAddressType());
                addressDto.setPinCode(addressMaster.getPinCode());
                addressDto.setStreet(addressMaster.getStreet());
                addressDto.setLandmark(addressMaster.getLandmark());
                addressDto.setCountry(addressMaster.getCountry());
                addressDto.setCity(addressMaster.getCity());
                addressDto.setApartment(addressMaster.getApartment());
                addressDto.setPhoneNumber(addressMaster.getPhoneNumber());
                addressDto.setAltPhoneNumber(addressMaster.getAltPhoneNumber());
                addressDtoList.add(addressDto);
            }
            addressListDto.setAddressDtoList(addressDtoList);
            return addressListDto;
        }
        return null;
    }
}
