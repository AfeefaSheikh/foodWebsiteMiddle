package com.example.foodWebSiteDubai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FoodImages")
public class FoodImages{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ImageUrl")
    private String imageUrl;
    @Column(name = "ImageName")
    private String imageName;

}
