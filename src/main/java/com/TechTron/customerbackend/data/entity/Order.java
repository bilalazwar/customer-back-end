package com.TechTron.customerbackend.data.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

//@Schema(name = "Order Schema", description = "The model for Order" )
//@ApiModel(value = "Order Schema", description = "Represents an order in the system")
//@Tag(name = "Order Tag")

@Schema(name = "Customer Order Schema", description = "The model for Order" )
@ApiModel(description = "Order")
@Entity
@Table(name = "order_tbl")

@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

//    @ManyToOne
//    @JoinColumn(name = "Customer_Id_Fk", referencedColumnName = "Customer_Id")
//    private Customer customer;

    @OneToOne
    @JoinColumn(name = "Cart_Id_Fk", referencedColumnName = "Cart_Id")
    private Cart cart;

    @Column(name = "Total_Items")
    private int totalItems;

    @Column(name = "Order_Date")
    private LocalDate orderDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "Delivery_method")
    private String deliveryMethod;

    @Column(name = "Payment_Type")
    private String paymentType;

    @Column(name = "Total_Amount")
    private float totalAmount;


}
