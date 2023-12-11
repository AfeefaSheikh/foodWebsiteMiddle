package com.example.foodWebSiteDubai.controller;


import com.example.foodWebSiteDubai.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.ADD_TO_CART;
import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.CART_MANAGEMENT_BASE_URI;


@RestController
@RequestMapping(CART_MANAGEMENT_BASE_URI)
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping(ADD_TO_CART)
    public String addToCart(@PathVariable Long id, @PathVariable Long userId,@PathVariable Long quantity){
        cartItemService.addToCart(quantity,id,userId);
        return "Addded";
    }

    @PostMapping("/buyNow")
    public String buyNow(@PathVariable Long userId, @PathVariable Long id){
        return cartItemService.buyNow(userId,id);
    }
}
