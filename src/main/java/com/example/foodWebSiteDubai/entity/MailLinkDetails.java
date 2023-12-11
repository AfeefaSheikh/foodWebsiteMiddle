package com.example.foodWebSiteDubai.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MailLinkDetails")
public class MailLinkDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailLinkId")
    private Long mailLinkId;
    @Column(name = "Uuid")
    private String uuid;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "expiry")
    public LocalDateTime expirationTime;

    @Column(name = "mailLinkUrlId")
    private Long mailId;
}
