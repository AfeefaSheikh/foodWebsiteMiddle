package com.example.foodWebSiteDubai.repository;

import com.example.foodWebSiteDubai.entity.AddressMaster;

import com.example.foodWebSiteDubai.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMasterRepo extends JpaRepository<AddressMaster,Long> {
//    boolean existsByUserMaster(UserMaster userMaster);

//    List<AddressMaster> findByUserMaster(UserMaster userMaster);

    boolean existsByUserId(Long userId);

    List<AddressMaster> findByUserId(Long userId);
}
