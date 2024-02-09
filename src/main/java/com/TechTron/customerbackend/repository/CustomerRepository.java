package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    // Custom query methods can be added here
}
