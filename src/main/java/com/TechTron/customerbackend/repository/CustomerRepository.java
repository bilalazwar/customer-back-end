package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
