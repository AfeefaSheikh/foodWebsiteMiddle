package com.example.foodWebSiteDubai.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductMaster productMaster;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserMaster userMaster;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "subtotal")
    private Double subTotal;

}
