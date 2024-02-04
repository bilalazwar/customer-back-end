package com.TechTron.customerbackend.data;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "product_tbl")

@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotBlank                       // Cannot be blank (must have at least one non-whitespace character)
    @Column(name = "Category")
    private String category;

    @NotNull                        // Not null, but can still be 0
    @Column(name = "Price")
    private float price;

    @NotEmpty                      // Cannot be empty (must have at least one character, even whitespace)
    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItem;


    //product Image

    // same product different variations like color price
    // then need new table

}
