package com.example.foodWebSiteDubai.controller;


import com.example.foodWebSiteDubai.dto.request.CartMasterDto;
import com.example.foodWebSiteDubai.service.CartMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.CART_MASTER_MANAGEMENT_BASE_URI;
import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.*;

@RestController
@RequestMapping(CART_MASTER_MANAGEMENT_BASE_URI)
public class CartMasterController {
    @Autowired
    CartMasterService cartMasterService;
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(GET_ALL_CART_ITEMS)
    public CartMasterDto getAll(@PathVariable Long userId){
        return cartMasterService.getAllItems(userId);
    }
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(DELETE_PRODUCT_BY_ID)
    public String deleteByProductId(@PathVariable Long id, @PathVariable Long userId){
        return cartMasterService.deleteByProductId(id,userId);
    }
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping(ADD_PRODUCT_TO_CART)
    public String updateProductInCart(@PathVariable Long id, @PathVariable Long userId){
        return cartMasterService.updateProductInCart(id,userId);
    }
}
