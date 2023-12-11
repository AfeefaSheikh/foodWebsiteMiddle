package com.example.foodWebSiteDubai.service;


import com.example.foodWebSiteDubai.dto.request.CartMasterDto;

public interface CartMasterService {
    public CartMasterDto getAllItems(Long userId);

    public String deleteByProductId(Long id, Long userId);

    public String updateProductInCart(Long id, Long userId);
}
