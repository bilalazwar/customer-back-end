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
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // can change initial value
    @Column(name = "Cart_Id")
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "Customer_Identity", referencedColumnName = "Customer_Id")   //The @JoinColumn annotation generates the foreign key column in the dependent entity's table (in this case, the customer_Identity column in the cart_tbl table).
    private Customer custmr;

    //The @JoinColumn annotation creates the foreign key that links the tables.

    //These variables act as placeholders to hold references to objects of the related entity.

    @Column(name = "Active")
    private boolean active;

    @OneToMany(mappedBy = "crt", cascade = CascadeType.ALL)
    private CartItem cartitem;

    @OneToOne(mappedBy = "cart" , cascade = CascadeType.ALL)
    private Order order;

}
