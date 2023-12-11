package com.example.foodWebSiteDubai.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String images;
    private String productBrand;
    //    private Integer productRating;

}
