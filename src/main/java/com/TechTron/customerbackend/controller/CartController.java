package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){

        if(cart != null){

            Cart cart1 = cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart1);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @PatchMapping("/{cartId}")
    public ResponseEntity<String> addCustomerIdToCart(@PathVariable int cartId, @RequestBody Customer customer) {

        cartService.updateCustomerIdInCart(customer.getCustomerId(), cartId);

        return ResponseEntity.status(HttpStatus.OK).body("Added customerId to Cart");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cart>> getCart(@PathVariable int id){

        Optional<Cart> cart = cartService.getCartById(id);

        if(cart.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }
    }

    // update customer Active status ones order placed
    @PatchMapping(params = {"cartID","active"})
    public void activateCart(@RequestParam("cartID") int cartID, @RequestParam("active") boolean active){
        if(active){
            cartService.activateCart(cartID);
        }
        else {
            cartService.deActivateCart(cartID);
        }
    }

}









//(@RequestBody(required = false) Integer customerId)