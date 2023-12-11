package com.example.foodWebSiteDubai.specification;

import com.example.foodWebSiteDubai.entity.ProductMaster;
import com.example.foodWebSiteDubai.exception.CommonException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import javax.persistence.criteria.Join;


public class ProductMasterSpecification {
    private ProductMasterSpecification() {
    }

    public static Specification<ProductMaster> isDeleted() {
        return (root, query, cb) -> cb.isFalse(root.get("isDeleted"));
    }

    public static Specification<ProductMaster> findByProductName(String name) {
        if (name.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "name should not be an empty");
        }

        return (root, query, cb) -> cb.like(root.get("productName"), "%" + name + "%");
    }

    public static Specification<ProductMaster> findByProductPrice(Double price) {
        if (price == null) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "emailId should not be an empty");
        }
        return (root, query, cb) -> cb.like(root.get("productPrice"), "%" + price + "%");
    }


    public static Specification<ProductMaster> findByProductBrand(String productBrand) {
        if (productBrand == null || productBrand.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "productBrand should not be an empty");
        }
        return (root, query, cb) -> cb.like(root.get("productBrand"), "%" + productBrand + "%");

    }
//    public static Specification<ProductMaster> findByCategoryName(String categoryName) {
//        if (categoryName.isEmpty()) {
//            throw new CommonException(HttpStatus.BAD_REQUEST, "categoryName should not be an empty");
//        }
//        return (root, query, cb) -> {
//            Join<ProductMaster, CategoryMaster> userJoin1 = root.join("categoryMaster");
//            return cb.equal(userJoin1.get("CategoryName"), categoryName);};
//    }


}








