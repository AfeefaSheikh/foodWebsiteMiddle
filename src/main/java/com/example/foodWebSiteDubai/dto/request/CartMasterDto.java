package com.example.foodWebSiteDubai.dto.request;


import com.example.foodWebSiteDubai.entity.UserMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartMasterDto {

//    private String status;
    private Double totalPrice;
//    private Boolean isGift;
    private Date orderedAt;
    private UserMaster user;
    private List<CartItemDto> cartItemDto;
    private String couponCode;
//    private AddressMaster addressMaster;
}
