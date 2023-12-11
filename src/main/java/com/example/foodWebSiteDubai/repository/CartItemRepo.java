package com.example.foodWebSiteDubai.repository;


import com.example.foodWebSiteDubai.entity.CartItem;
import com.example.foodWebSiteDubai.entity.ProductMaster;
import com.example.foodWebSiteDubai.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
//    Optional<CartItem> findByProductMaster(ProductMaster productMaster);
//
//    boolean existsByProductMaster(ProductMaster productMaster1);

//    Optional<CartMaster> findProductsByUserId(Long userId);

//    boolean existsByUserId(UserMaster userMaster1);

    boolean existsByUserMaster(UserMaster userMaster);

    List<CartItem> findByUserMaster(UserMaster userMaster);

    boolean existsByProductMasterAndUserMaster(ProductMaster productMaster1, UserMaster userMaster);

    CartItem findByProductMasterAndUserMaster(ProductMaster productMaster1, UserMaster userMaster1);

    boolean existsByProductMaster(ProductMaster productMaster);
}
