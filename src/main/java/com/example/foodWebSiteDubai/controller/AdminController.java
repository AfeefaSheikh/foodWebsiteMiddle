package com.example.foodWebSiteDubai.controller;



import com.example.foodWebSiteDubai.dto.request.ProductRequest;
import com.example.foodWebSiteDubai.response.ApiResponse;
import com.example.foodWebSiteDubai.response.Response;
import com.example.foodWebSiteDubai.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.foodWebSiteDubai.constant.ControllerUrlConstants.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ADMIN_MANAGEMENT_BASE_URI)
@CrossOrigin(allowedHeaders = "*")
public class AdminController {
    @Autowired
    private ProductService productService;

//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(value=ADD_PRODUCT)
    public ResponseEntity<ApiResponse> createProducts(@ModelAttribute ProductRequest productRequest
                                                       ) {
        return Response.getResponseEntity(OK, "SUCCESS", productService.createProduct(productRequest,productRequest.getImages()));
    }

    @PostMapping("/addPimage")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest,
                                                @RequestParam("images") MultipartFile[] images) throws IOException {
        return Response.getResponseEntity(OK, "Product created successfully", productService.createProduct(productRequest, images));

    }



    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<ApiResponse> updateProductById(@RequestBody ProductRequest productRequest,
                                                         @RequestParam(required = false) Long pId) {

        return Response.getResponse(HttpStatus.OK, productService.updateProductDetails(pId, productRequest));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id) {

        return Response.getResponseEntity(OK, "USER_DELETED_SUCCESSFULLY", productService.deleteProduct(id));
    }

    //Category Api
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @PostMapping(ADD_CATEGORY)
//    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryRequest categoryRequest) {
//        return Response.getResponseEntity(OK, "Category added successfully", categoryService.addCategory(categoryRequest));
//    }

//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @GetMapping(LIST_CATEGORY)
//    public ResponseEntity<ApiResponse> listCategory() {
//        return Response.getResponseEntity(OK, "", categoryService.listCategory());
//    }

//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @GetMapping(LIST_CATEGORY_BY_NAME)
//    public ResponseEntity<ApiResponse> listByCategoryName(@PathVariable String categoryName) {
//        return Response.getResponseEntity(OK, "", categoryService.listByCategoryName(categoryName));
//    }
//
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @PutMapping(UPDATE_CATEGORY_BY_ID)
//    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest categoryRequest) {
//        return Response.getResponseEntity(OK, "Category updated", categoryService.updateCategoryByCategoryID(categoryId, categoryRequest));
//    }
//
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @DeleteMapping(DELETE_CATEGORY_BY_ID)
//    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
//        return Response.getResponseEntity(OK, "Category deleted", categoryService.deleteCategory(categoryId));
//    }

//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    @PutMapping(UPDATE_SIZE_IN_STOCK )
//    public ResponseEntity<ApiResponse> updateSize(@RequestBody SizeRequest sizeRequest, @PathVariable Long id){
//        return Response.getResponseEntity(OK, "Stock updated based on size.",productService.updateSize(sizeRequest, id));
//    }
}
