package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.Customer;
import com.TechTron.customerbackend.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;


    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){

        return customerService.createCustomer(customer);
    }



}
