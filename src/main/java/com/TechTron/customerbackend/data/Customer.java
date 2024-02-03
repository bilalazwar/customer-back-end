package com.TechTron.customerbackend.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity             // java persistence both down also. Tells JPA it's a Entity
@Table(name = "customer_tbl")

@Getter         //Lombok Library annotation. after compiling can check in the target directory
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //cascade should come to the side owning the foreign key.. the side where the primary key is
    @OneToMany(mappedBy = "custmr", cascade = CascadeType.ALL)         //Ensures that operations (save, update, delete) on the Customer also apply to the associated Cart. This maintains consistency and prevents orphaned records.
    private Cart crt;      //facilitates easy access to cart data when dealing with customers

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Order order;


    //These variables act as placeholders to hold references to objects of the related entity.


    //mappedBy = if didnt put it would create new seperate table..by putting this its saying that the relationship is mapped by the custmr.

    //cascade = attribute ensures data consistency and prevents orphaned records.

}














//
//
//If you don't use cascade = CascadeType.ALL in your Customer entity for the Cart relationship, here are some of the possible consequences:
//
//        1. Orphaned Cart Data:
//
//When you delete a Customer, the associated Cart record wouldn't be automatically deleted. This could lead to orphaned data in your database, potentially taking up unnecessary space and causing inconsistencies.
//        2. Manual Deletion Required:
//
//You would need to explicitly delete the associated Cart record whenever you delete a Customer. This adds extra steps and complexity to your code, increasing the risk of forgetting to delete the Cart and introducing potential inconsistencies.
//3. Business Logic Implications:
//
//Depending on your business logic, having orphaned Cart data might violate certain rules or expectations. For example, if a user tries to access the cart of a deleted customer, you might encounter errors or unexpected behavior.
//4. Data Integrity Issues:
//
//Without cascading deletion, if the Cart table has foreign key constraints referencing the Customer table, attempting to delete a customer with an associated Cart might fail due to constraint violations.
//        Example:
//
//Imagine a scenario where you delete a customer with an active cart containing items. Without cascade = CascadeType.ALL, the following might happen:
//
//The customer is deleted from the database.
//The cart record remains in the database, referencing a non-existent customer.
//This orphaned cart becomes inaccessible and potentially creates inconsistencies in your data model.
//Alternative Approaches:
//
//Explicit Deletion: If you have specific control requirements, you can perform Cart deletion manually within your Customer deletion logic.
//Specific Cascade Types: Instead of CascadeType.ALL, you can use more granular cascade types like CascadeType.REMOVE to selectively cascade only the delete operation.
//        In summary, carefully consider the potential consequences of not using CascadeType.ALL and whether they align with your application's requirements and data integrity constraints. If automatic cascading aligns with your business logic and simplifies your code, using CascadeType.ALL is generally a safe and convenient approach.
