package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart createCart(@RequestBody Cart cart){

        return cartService.createCart(cart);
    }

    @Transactional
    @PatchMapping("/{cartId}")
    public void addCustomerIdToCart(@PathVariable int cartId, @RequestBody Customer customer) {

        cartService.updateCustomerIdInCart(customer.getCustomerId(), cartId);
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCart(@PathVariable int id){

        return cartService.getCartById(id);
    }

    // update customer Active status ones order placed


}









//(@RequestBody(required = false) Integer customerId)