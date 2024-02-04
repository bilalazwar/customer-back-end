package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.Cart;
import com.TechTron.customerbackend.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
