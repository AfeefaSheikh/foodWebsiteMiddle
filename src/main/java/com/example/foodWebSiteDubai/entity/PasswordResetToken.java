package com.example.foodWebSiteDubai.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passwordToken")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = UserMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserMaster user;

    @Column(name = "expirationDate")
    private Date expirationDate;

}
