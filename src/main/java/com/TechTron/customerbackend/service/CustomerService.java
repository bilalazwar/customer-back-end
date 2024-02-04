package com.TechTron.customerbackend.service;

import com.TechTron.customerbackend.data.Customer;
import com.TechTron.customerbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
