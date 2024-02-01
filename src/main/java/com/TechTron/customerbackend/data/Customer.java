package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity             // java persistence both down also. Tells JPA it's a Entity
@Table(name = "customer_details_tbl")

@Getter         //Lombok Library annotation. after compiling can check in the target directory
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)     // other is IDENTITY option. sequence option caches available unique ids to minimize database calls.
    @Column(name = "Customer_Id")
    private int customerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "E-mail")
    private String email;

    @Column(name = "Phone_Number")
    private int phoneNumber;

    @Column(name = "Address")
    private String address;

    @OneToOne(mappedBy = "custmr", cascade = CascadeType.ALL)         //Ensures that operations (save, update, delete) on the Customer also apply to the associated Cart. This maintains consistency and prevents orphaned records.
    private Cart cart;      //facilitates easy access to cart data when dealing with customers

    //These variables act as placeholders to hold references to objects of the related entity.


    //mappedBy = if didnt put it would create new seperate table..by putting this its saying that the relationship is mapped by the custmr.

    //cascade = attribute ensures data consistency and prevents orphaned records.

}
