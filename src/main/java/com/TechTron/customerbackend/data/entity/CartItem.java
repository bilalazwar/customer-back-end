package com.TechTron.customerbackend.data.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @JoinColumn(name = "Cart_Id_Fk")
    private Cart crt;

//  joinColumn
    @Column(name = "Product_Id_Fk")
    private int productIdFK;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Unit_Price")
    private float unitPrice;

    @Column(name = "Total_Price")
    private float totalPrice;


}
