package com.example.foodWebSiteDubai.service.serviceImpl;

import com.example.foodWebSiteDubai.entity.FoodImages;
import com.example.foodWebSiteDubai.service.ProductService;
import com.example.foodWebSiteDubai.dto.request.FilterRequest;
import com.example.foodWebSiteDubai.dto.request.ProductRequest;
import com.example.foodWebSiteDubai.dto.request.SearchRequestDto;
import com.example.foodWebSiteDubai.dto.response.ProductResponse;
import com.example.foodWebSiteDubai.entity.ProductMaster;
import com.example.foodWebSiteDubai.exception.CommonException;
import com.example.foodWebSiteDubai.repository.ProductMasterRepo;
import com.example.foodWebSiteDubai.specification.ProductMasterSpecification;
import com.example.foodWebSiteDubai.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.foodWebSiteDubai.constant.ErrorConstantMessage.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMasterRepo productMasterRepo;
    @Autowired
    private ImageUtil imageUtil;




    @Override
    public List<ProductResponse> getAllProducts(int pageNo, int pageSize, FilterRequest filterRequest) {
        Page<ProductMaster> filteredList=null;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("productName").descending());
        if(filterRequest.getSearchRequestDtoList().isEmpty()){
            filteredList = productMasterRepo.findAll(pageable);
        }
        Specification<ProductMaster> productMasterSpecification = getUserDetailsEntitySpecification(filterRequest);
        if (productMasterSpecification != null) {
            filteredList = productMasterRepo.findAll(productMasterSpecification, pageable);
        }

        if (filteredList.isEmpty()) {
            throw new RuntimeException(NO_PRODUCT);
        }
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductMaster productMaster : filteredList) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductName(productMaster.getProductName());
            productResponse.setProductDescription(productMaster.getProductDescription());
//            productResponse.setProductPrice(productMaster.getProductPrice());
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public String updateProductDetails(Long pId, ProductRequest productRequest) {
        Optional<ProductMaster> productMaster = productMasterRepo.findById(pId);
        if (productMaster.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, PRODUCT_ID_NOT_PRESENT);
        }
        ProductMaster productMaster1 = productMaster.get();
        productMaster1.setProductName(productRequest.getProductName());
//        productMaster1.setProductPrice(productRequest.getProductPrice());
//        productMaster1.setProductBrand(productRequest.getProductBrand());
        productMaster1.setProductDescription(productRequest.getProductDescription());
        productMasterRepo.save(productMaster1);
        return "Updated Successfully";
    }

    @Override
    public String deleteProduct(Long pId) {
        Optional<ProductMaster> productMaster = productMasterRepo.findById(pId);
        if (productMaster.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, PRODUCT_ID_NOT_PRESENT);
        }
        productMasterRepo.deleteById(pId);
        return "Product Deleted Successfully";
    }


    private Specification<ProductMaster> getUserDetailsEntitySpecification(FilterRequest filterRequest) {
        Specification<ProductMaster> productMasterSpecification = null;
        if (!CollectionUtils.isEmpty(filterRequest.getSearchRequestDtoList())) {
            for (SearchRequestDto searchRequest : filterRequest.getSearchRequestDtoList()) {
                if (Objects.nonNull(searchRequest.getFieldName()) && Objects.nonNull(searchRequest.getFieldValue())) {
                    if (searchRequest.getFieldName().isEmpty()) {

                        throw new CommonException(HttpStatus.MULTI_STATUS, NAME_EMPTY);
                    }
                    if (searchRequest.getFieldValue().isEmpty()) {
                        throw new CommonException(HttpStatus.MULTI_STATUS, VALUE_EMPTY);
                    }
                    switch (searchRequest.getFieldName()) {

                        case "productName":
                            productMasterSpecification = productMasterSpecification == null ?
                                    ProductMasterSpecification.findByProductName(searchRequest.getFieldValue().toLowerCase()) :
                                    productMasterSpecification.and(ProductMasterSpecification.findByProductName((searchRequest.getFieldValue().toLowerCase())));
                            break;

                        case "productPrice":
                            productMasterSpecification = productMasterSpecification == null ?
                                    ProductMasterSpecification.findByProductPrice(Double.valueOf(searchRequest.getFieldValue())) :
                                    productMasterSpecification.and(ProductMasterSpecification.findByProductPrice(Double.valueOf(searchRequest.getFieldValue())));
                            break;
//                        case "productBrand":
//                            productMasterSpecification = productMasterSpecification == null ?
//                                    ProductMasterSpecification.findByProductBrand(searchRequest.getFieldValue().toLowerCase()) :
//                                    productMasterSpecification.and(ProductMasterSpecification.findByProductBrand(searchRequest.getFieldValue().toLowerCase()));
//                            break;
                        default:
                            throw new CommonException(HttpStatus.BAD_REQUEST, VALID_FIELD_NAME);
                    }
                }
            }
        }
        return productMasterSpecification;
    }
    public String createProduct(ProductRequest productRequest, MultipartFile[] imageFiles) throws IOException {
        ProductMaster product=new ProductMaster();
        ProductMaster productMaster = new ProductMaster();
        productMaster.setProductName(productRequest.getProductName());
        productMaster.setSellingPrice(productRequest.getSellingPrice());
        productMaster.setProductDescription(productRequest.getProductDescription());
        List<FoodImages> images = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            String imageUrl = imageUtil.uploadImage(imageFile);
            FoodImages image=new FoodImages();
            image.setImageName(imageFile.getName());
            image.setImageUrl(imageUrl);
            images.add(image);
        }

        product.setFoodImagesList(images);
        productMasterRepo.save(product);
        return "product added";
    }
    @Override
    public String createProduct(ProductRequest productRequest,List<MultipartFile> imageList) {
        if (productRequest == null) {
            throw new RuntimeException(PRODUCT_DETAIL_EMPTY);
        }

        ProductMaster productMaster = new ProductMaster();
        productMaster.setProductName(productRequest.getProductName());
        productMaster.setOriginalPrice(productRequest.getOriginalPrice());
        productMaster.setProductDescription(productRequest.getProductDescription());
        productMaster.setSmallDescription(productRequest.getSmallDescription());
        productMaster.setQuantity(productMaster.getQuantity());
        productMaster.setSellingPrice(productRequest.getSellingPrice());

        // Upload images to S3 and save image URLs to the database
        List<FoodImages> productImages = imageUtil.uploadImages(imageList);
//        List<FoodImages> imagesList= new ArrayList<>();
//        for(MultipartFile file:imageList){
//            FoodImages images= new FoodImages();
//            images.setImageName(file.getOriginalFilename());
//            images.setImageUrl(file.getName()+"ghj");
//            imagesList.add(images);
//        }
        productMaster.setFoodImagesList(productImages);
       productMasterRepo.save(productMaster);
        return "Product Created";
    }

}
