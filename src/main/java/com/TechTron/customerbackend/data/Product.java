package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_tbl")

@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "Category")
    private String category;

    @Column(name = "Price")
    private float price;

    @Column(name = "Description")
    private String description;

    //product Image

    // same product different variations like color price
    // then need new table

}
