package com.example.foodWebSiteDubai.repository;

import com.example.foodWebSiteDubai.entity.MailLinkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailLinkDetailsRepo extends JpaRepository<MailLinkDetails,Long> {
    Optional<MailLinkDetails> findByUserIdAndUuid(Long userId, String uuid);

    Optional<MailLinkDetails> findByUuid(String token);
}
