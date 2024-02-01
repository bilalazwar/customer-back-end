package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_tbl")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)     // can change initial value
    @Column(name = "Cart_Id")
    private int cartId;

    @OneToOne
    @JoinColumn(name = "Customer_Identity", referencedColumnName = "Customer_Id")   //The @JoinColumn annotation generates the foreign key column in the dependent entity's table (in this case, the customer_Identity column in the cart_tbl table).
    private Customer custmr;

    //These variables act as placeholders to hold references to objects of the related entity.

    @Column(name = "Active")
    private boolean active;

    //The @JoinColumn annotation creates the foreign key that links the tables.

}
