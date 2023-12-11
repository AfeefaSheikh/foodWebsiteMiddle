package com.example.foodWebSiteDubai.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long cartId;
    private ProductRequest productRequest;
    private Long quantity;
    private Double subtotal;
}
