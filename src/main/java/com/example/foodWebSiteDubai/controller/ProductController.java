package com.example.foodWebSiteDubai.controller;

import com.example.foodWebSiteDubai.dto.request.FilterRequest;
import com.example.foodWebSiteDubai.response.ApiResponse;
import com.example.foodWebSiteDubai.response.Response;
import com.example.foodWebSiteDubai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.GET_ALL_PRODUCTS;
import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.PRODUCT_MANAGEMENT_BASE_URI;

import static org.springframework.http.HttpStatus.OK;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(PRODUCT_MANAGEMENT_BASE_URI)
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping(GET_ALL_PRODUCTS)
    public ResponseEntity<ApiResponse> fetchAllProducts(@RequestParam int pageNo, @RequestParam int pageSize,
                                                        @RequestBody FilterRequest filterRequest) throws Exception {
        return Response.getResponseEntity(OK, "SUCCESS", productService.getAllProducts(pageNo,pageSize,filterRequest));
    }
}
