package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item_tbl")

@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "Cart_Id")
    private Cart crt;

    @ManyToOne
    @JoinColumn(name = "Product_Id", referencedColumnName = "Product_Id")
    private Product product;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Unit_Price")
    private float unitPrice;

    @Column(name = "Total_Price")
    private float totalPrice;


}
