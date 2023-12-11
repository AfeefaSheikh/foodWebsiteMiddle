package com.example.foodWebSiteDubai.repository;


import com.example.foodWebSiteDubai.entity.CartMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMasterRepo extends JpaRepository<CartMaster,Long> {

//    CartMaster findByUserId(Long userId);
}
