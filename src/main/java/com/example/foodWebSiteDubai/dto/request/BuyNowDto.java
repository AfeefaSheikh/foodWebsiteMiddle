package com.example.foodWebSiteDubai.dto.request;

import com.example.foodWebSiteDubai.entity.CartItem;
import com.example.foodWebSiteDubai.entity.UserMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyNowDto {
    private double totalPrice;
    private Long quantity;
    private UserMaster userMaster;
}
