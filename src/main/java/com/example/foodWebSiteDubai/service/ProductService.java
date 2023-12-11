package com.example.foodWebSiteDubai.service;


import com.example.foodWebSiteDubai.dto.request.FilterRequest;
import com.example.foodWebSiteDubai.dto.request.ProductRequest;
import com.example.foodWebSiteDubai.dto.request.SizeRequest;
import com.example.foodWebSiteDubai.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public String createProduct(ProductRequest productRequest,List<MultipartFile> imagesList);

    public String createProduct(ProductRequest productRequest, MultipartFile[] imageFiles) throws IOException;

    List<ProductResponse> getAllProducts(int pageNo, int pageSize, FilterRequest filterRequest);

    public String updateProductDetails(Long pId, ProductRequest productRequest);

    public String deleteProduct(Long pId);
}
