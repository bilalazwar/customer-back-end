package com.TechTron.customerbackend.repository;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Modifying
    @Query("UPDATE Cart SET cust.customerId = :customerId WHERE cartId = :cartId")
    void updateCustomerIdByCartId(@Param("customerId") int customerId, @Param("cartId") int cartId);



//    @Modifying
//    @Query("UPDATE Cart SET cust = :customer WHERE cartId = :cartId")
//    void updateCustomerByCartId(@Param("customer") Customer customer, @Param("cartId") int cartId);

//    @Modifying
//    @Query("UPDATE Cart c SET c.cust = :customer WHERE c.cartId = :cartId")
//    void updateCustomerByCartId(@Param("customer") Customer customer, @Param("cartId") int cartId);
}
