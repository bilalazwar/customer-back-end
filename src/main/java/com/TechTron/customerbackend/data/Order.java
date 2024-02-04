package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_tbl")

@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "Cust_Id", referencedColumnName = "Customer_Id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "Cart_Id", referencedColumnName = "Cart_Id")
    private Cart cart;

    @Column(name = "Total_Items")
    private int totalItems;

    @Column(name = "Order_Date")
    private Date orderDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "Delivery_method")
    private String deliveryMethod;

    @Column(name = "Payment_Type")
    private String paymentType;

    @Column(name = "Total_Amount")
    private float totalAmount;


}
