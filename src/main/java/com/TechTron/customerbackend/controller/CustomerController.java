package com.TechTron.customerbackend.controller;

import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@Tag(name = "Customer Controller",description = "Manages customer accounts and profiles.")

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //throw exception in service and catch in Controller
//    @Hidden swagger
    @Operation(summary = "Register New Customer",description = "Creates only one customer at a time")
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
//    @ApiOperation(value = "doesn't expect anything", notes = "Provides all the registered customers", response = ResponseEntity.class)
    @Operation(summary = "Display All Customer", description = "Displays List of all available Customers")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Display Customer by ID", description = "Displays the customer related to the ID")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable int id){

        Optional<Customer> getCustomer = customerService.getCustomer(id);

        if(getCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(getCustomer);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    http://localhost:8081/customers?id=7
//    @ApiParam(value = "Color of the car.", required = true)

    @Operation(summary = "Display true or false on Customer Id availability", description = "returns false when customer not found with that ID")
    @GetMapping("/availability/{id}")
    public ResponseEntity<Boolean> customerExist(@PathVariable int id){

        boolean value = customerService.customerExist(id);

        return value ? ResponseEntity.status(HttpStatus.OK).body(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }



    @Operation(summary = "Update Customer Data", description = "Need to pass the Id in the path-variable and the updated customer data in the request body. <br>Does partial update by comparing and updating the previous customer data. <br>Pass only the properties that wants to be updated")
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

