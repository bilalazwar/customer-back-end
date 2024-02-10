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


    @Modifying
    @Query("UPDATE Cart SET active = :active WHERE cartId = :cartId")
    void UpdateCartStatus(@Param("active") boolean active, @Param("cartId") int cartId);
}













//@Query("SELECT c FROM Cart c WHERE c.cartId = :cartId")
//Cart findById(@Param("cartId") int cartId);
//
//default Cart updateCustomerIdAndReturnCart(int customerId, int cartId) {
//    int rowsUpdated = updateCustomerIdByCartId(customerId, cartId);
//    if (rowsUpdated == 1) { // Ensure exactly one cart was updated
//        return findById(cartId);
//    } else {
//        throw new EntityNotFoundException("Cart with ID " + cartId + " not found");
//    }
//}
