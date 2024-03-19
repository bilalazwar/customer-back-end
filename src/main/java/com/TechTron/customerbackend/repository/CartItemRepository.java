package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.crt.cartId = :cartId")
    List<CartItem> getAllCartItemsFromCartId(@Param("cartId") int cartId);


    //Counting total CartItems for each customer
    @Query("SELECT COUNT(c) FROM CartItem c WHERE c.crt.cartId = :cartId")
    int countCartItemsByCartId(@Param("cartId") int cartId);
}
