package com.example.foodWebSiteDubai.repository;

import com.example.foodWebSiteDubai.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMasterRepo extends JpaRepository<UserMaster,Long> {
    Optional<UserMaster>  findByEmailId(String emailId);
}
