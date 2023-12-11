package com.example.foodWebSiteDubai.service;


public interface CartItemService {

    String addToCart(Long quantity, Long cartId, Long userId);

    String buyNow(Long userId, Long id);
}
