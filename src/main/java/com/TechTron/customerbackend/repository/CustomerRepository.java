package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    // Custom query methods can be added here

    // Find customers above age 60 using JPQL query
    @Query("SELECT c FROM Customer c WHERE YEAR(CURRENT_DATE) - YEAR(c.dateOfBirth) >= 60")
    List<Customer> findCustomersAboveAge60();

    // Or directly using native SQL query
//    @Query(nativeQuery = "SELECT * FROM customer_tbl WHERE EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM date_of_birth) >= 60", nativeQuery = true)
//    List<Customer> findCustomersAboveAge60Native();
}


//JPQL Java Persistence Query Language)

//A standardized object-oriented query language defined by the JPA specification.

//Aimed to provide a portable and consistent way to query data in JPA applications, regardless of the underlying database or ORM provider.

//
