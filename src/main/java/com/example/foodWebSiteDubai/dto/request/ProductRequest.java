package com.example.foodWebSiteDubai.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String productName;
    private String smallDescription;
    private String productDescription;
    private Double originalPrice;
    private Double sellingPrice;
    private Integer quantity;
    List<MultipartFile> images;


//    List<SizeRequest> sizeRequestList;
//    private Integer productRating;

}
