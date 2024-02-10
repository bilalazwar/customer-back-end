package com.TechTron.customerbackend.service;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    public boolean cartExist(int id){

        Optional<Cart> cartOptional = cartRepository.findById(id);
        return cartOptional.isPresent();    // if values is present return true
    }

    @Transactional
    public void updateCustomerIdInCart(int customer_Id,int cart_Id){
        cartRepository.updateCustomerIdByCartId(customer_Id,cart_Id);
    }

    public Optional<Cart> getCartById(int id) {

        return cartRepository.findById(id);
    }

    @Transactional
    public void activateCart(int cartId){
        cartRepository.UpdateCartStatus(true,cartId);
    }

    @Transactional
    public void deActivateCart(int cartId){
        cartRepository.UpdateCartStatus(false,cartId);
    }
}
