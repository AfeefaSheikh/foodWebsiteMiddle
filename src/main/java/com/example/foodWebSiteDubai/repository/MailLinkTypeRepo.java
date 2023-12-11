package com.example.foodWebSiteDubai.repository;

import com.example.foodWebSiteDubai.entity.MailLinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailLinkTypeRepo extends JpaRepository<MailLinkType, Long> {
}
