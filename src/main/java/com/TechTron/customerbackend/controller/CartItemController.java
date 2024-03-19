package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.dto.CartItemDto;
import com.TechTron.customerbackend.data.entity.CartItem;
import com.TechTron.customerbackend.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cart-Item Controller",description = "Manages customer's cart items.")
@RestController
@RequestMapping("/cartItems")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Operation(summary = "Create New Cart Item", description = "create CartItem using CartItemDto that contains product information's.<br> updates price and total amount taken from ProductMicro.")
    @PostMapping
    public CartItemDto createCartItemEntity(@RequestBody CartItem cartItem){

//        return cartItemService.createCartItemEntity(cartItem);
        return cartItemService.createCartItem(cartItem);
    }

    @Operation(summary = "Get all CartItems", description = " Returns all the customers CarItems.<br> Have to change where only return pending and not delivere once ")
    @GetMapping
    public List<CartItemDto> getAllCartItems(){

        return cartItemService.getAllCartItem();
    }

    @Operation(summary = "Get CartItem By Id", description = "Returns the CartItem related to the cartId")
    @GetMapping("/{id}")
    public Optional<CartItemDto> getCartItemByCartId(@PathVariable int id){
        return cartItemService.getCartItemById(id);
    }

    @Operation(summary = "Get CartItems by Cart Id", description = "Returns all the CartItems related to the Cart")
    @GetMapping(value = "/Cart/{cartId}")
    public List<CartItemDto> getCartItemsByCartId(@PathVariable int cartId){
        return cartItemService.getAllCartItemsFromCartId(cartId);
    }

    @Operation(summary = "Update CartItem", description = "Updates and returns the CartItem")
    @PatchMapping("/{id}")
    public CartItem updateCartItemByID(@PathVariable int id,@RequestBody CartItem updatedCartItem){

        return cartItemService.updateCartItem(id,updatedCartItem);
    }


}
