package com.TechTron.customerbackend.service;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.CartItem;
import com.TechTron.customerbackend.data.entity.Order;
import com.TechTron.customerbackend.repository.CartItemRepository;
import com.TechTron.customerbackend.repository.CartRepository;
import com.TechTron.customerbackend.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order createNewOrder(Order order){

        Optional<Cart> cart = cartService.getCartById(order.getCart().getCartId());
        int cartItemCount = cartItemRepository.countCartItemsByCartId(cart.get().getCartId());

//        cart.get().isActive()== true
        if(cart.isPresent() && cart.get().isActive() && cartItemCount>=1){

            order.setTotalItems(cartItemCount);
            order.setStatus("Pending");

            LocalDate today = LocalDate.now();     //getting current date
            order.setOrderDate(today);

            //get Total-price of all CartItems in the Cart
            List<CartItem> cartItemList = cartItemRepository.getAllCartItemsFromCartId(order.getCart().getCartId());
            float totalPrice=0f;
            for (CartItem cartItem: cartItemList){
                totalPrice = totalPrice + cartItem.getTotalPrice();
            }
            order.setTotalAmount(totalPrice);


            //if order placed successfully then make the Cart status to de-active || false
            order.getCart().setActive(false);

            return orderRepository.save(order);
        }
        else {
            return null;
        }
    }

    public List<Order> getAllOrderList(){

        return orderRepository.findAll();
    }



}
