package com.TechTron.customerbackend.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cart_tbl")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // can change initial value
    @Column(name = "Cart_Id")
    private int cartId;

//    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Customer_Identity", referencedColumnName = "Customer_Id")
    private Customer cust;

    @Column(name = "Active")
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "crt", cascade = CascadeType.ALL)
    private List<CartItem> cartItem;

    @JsonIgnore
    @OneToOne(mappedBy = "cart" , cascade = CascadeType.ALL)
    private Order order;

}












//@ManyToOne
//@JoinColumn(name = "Customer_Identity", referencedColumnName = "Customer_Id")   //The @JoinColumn annotation generates the foreign key column in the dependent entity's table (in this case, the customer_Identity column in the cart_tbl table).
//private Customer cust;





//The @JoinColumn annotation creates the foreign key that links the tables.

//These variables act as placeholders to hold references to objects of the related entity.