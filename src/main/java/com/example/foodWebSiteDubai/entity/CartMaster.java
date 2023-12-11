package com.example.foodWebSiteDubai.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CartMaster")
public class CartMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CartMaster_id")
    private Long cartMasterId;

    @Column(name = "status")
    private String cartStatus;
    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "isGift")
    private Boolean isGift;
    @Column(name = "orderedAt")
    private Date orderedAt;
    @OneToOne(cascade = CascadeType.ALL)
    private UserMaster user;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "couponId")
//    private CouponCode couponCode;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> CartItem;
//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressMaster addressMaster;
}
