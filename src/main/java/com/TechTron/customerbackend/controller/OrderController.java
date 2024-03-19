package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Order;
import com.TechTron.customerbackend.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Controller",description = "Manages customer Order's")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Place new order", description = "Place Order by Cart Id")
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){

        Order orders = orderService.createNewOrder(order);

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Operation(summary = "Get all orders", description = "Displays List of orders")
    @GetMapping
    public List<Order> getAllOrders(){

        return orderService.getAllOrderList();
    }

}
