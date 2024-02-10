package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //throw exception in service and catch in Controller
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // method is crucial for constructing and finalizing a ResponseEntity object. ------ Without build(), you'll have an unfinished ResponseEntity builder object, not a complete response.
        }
        else {
            Customer newCustomer = customerService.createCustomer(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
        }
    }
    @GetMapping
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable int id){

        Optional<Customer> getCustomer = customerService.getCustomer(id);

        if(getCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(getCustomer);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(params = "id")          //method will only be invoked when a GET request contains the "id" query parameter
    public ResponseEntity<Boolean> customerExist(@RequestParam int id){

        boolean value = customerService.customerExist(id);

        return value ? ResponseEntity.status(HttpStatus.OK).body(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id,@RequestBody Customer customer){

        Customer cus = customerService.updateCustomer(id,customer);

        if(cus != null){
            return ResponseEntity.status(HttpStatus.OK).body(cus);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //no customer delete
}







//@GetMapping("/customers/{customerId}")
//public Customer getCustomerWithCart(@PathVariable int customerId) {
//    Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
//    return customer; // Carts will be eagerly fetched due to FetchType.EAGER
//}

