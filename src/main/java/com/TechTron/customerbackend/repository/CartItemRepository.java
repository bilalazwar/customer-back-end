package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
