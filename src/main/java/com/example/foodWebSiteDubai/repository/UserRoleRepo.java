package com.example.foodWebSiteDubai.repository;

import com.example.foodWebSiteDubai.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,Long> {
}
