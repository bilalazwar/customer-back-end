package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.CartItem;
import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.repository.CartItemRepository;
import com.TechTron.customerbackend.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cart Controller",description = "Manages customer Cart")
@RestController
@RequestMapping("/carts")
public class CartController {

    // get cart by customer ID
    @Autowired
    private CartService cartService;

    @PostMapping
    @Operation(summary = "Create New Cart", description = "Create Cart By giving only Customer Id and status. The status must be true.<br>One Customer can have only one active cart")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){

        if(cart != null){

            Cart cart1 = cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart1);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Get List Of Carts", description = "returns all the carts")
    @GetMapping
    public List<Cart> cartItemList(){

        return cartService.getAllCart();
    }

    @Operation(summary = "Get Cart by Cart ID", description = "returns the cart only if active")
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

    @Operation(summary = "Get Cart by Customer ID", description = "Returns Cart Related to particular Customer")
    @GetMapping("/getByCustomerId/{id}")
    public ResponseEntity<Optional<Cart>> getCartByCustomerId(@PathVariable int customerId){

        Optional<Cart> cart = cartService.getCartByCustomerId(customerId);

        if(cart.isPresent()){

            return ResponseEntity.status(HttpStatus.FOUND).body(cart);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // update customer Active status ones order placed
    @Operation(summary = "Update Cart status", description = "Updates to true if the cart is active.<br>Updates to false if the Order is places. Then a new cart must be created for the customer")
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