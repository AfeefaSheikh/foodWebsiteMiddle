package com.example.foodWebSiteDubai.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AddressMaster")
public class AddressMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Aid")
    private Long addressId;

    @Column(name = "AddressType")
    private String addressType;

    @Column(name = "Street")
    private String street;

    @Column(name = "Apartment")
    private String apartment;

    @Column(name = "City")
    private String city;

    @Column(name = "PinCode")
    private String pinCode;

    @Column(name = "Country")
    private String country;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "altPhoneNumber")
    private String altPhoneNumber;

    @Column(name = "UserId")
    private Long userId;
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private UserMaster userMaster;


}
