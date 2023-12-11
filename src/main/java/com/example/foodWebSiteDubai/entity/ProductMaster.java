package com.example.foodWebSiteDubai.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductMaster")
public class ProductMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "smallDescription")
    private String smallDescription;

    @Column(name = "originalPrice")
    private Double originalPrice;

    @Column(name = "sellingPrice")
    private Double sellingPrice;

    @Column(name = "quantity ")
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ImageId", nullable = false)
    private List<FoodImages> foodImagesList;


}
