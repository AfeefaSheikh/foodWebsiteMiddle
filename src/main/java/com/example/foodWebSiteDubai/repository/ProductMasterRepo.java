package com.example.foodWebSiteDubai.repository;


import com.example.foodWebSiteDubai.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepo extends JpaRepository<ProductMaster,Long>, JpaSpecificationExecutor<ProductMaster> {
}
