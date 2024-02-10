package com.TechTron.customerbackend.service;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.Customer;
import com.TechTron.customerbackend.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional      // ensures consistency and proper rollback in case of errors:
    public Customer createCustomer(Customer customer){

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(int id){

        return customerRepository.findById(id);

    }

    // boolean return or false if customer exist
    public boolean customerExist(int id){

        return customerRepository.existsById(id);
    }

    @Transactional
    public Customer updateCustomer(int id,Customer customer) {

        if(customerExist(id)){

            Customer outDatedCustomer = customerRepository.getReferenceById(id);    // creates a proxy obj.but it doesn't actually fetch the data from the database until you try to access its properties or methods.
            Customer UpdatingCustomer = mergeCustomerData(outDatedCustomer,customer);

            return customerRepository.save(UpdatingCustomer);
        }

        return null;
    }
    @Transactional
    private Customer mergeCustomerData(Customer existingCustomer, Customer updatedCustomer) {

        if(updatedCustomer.getName() != null){
            existingCustomer.setName(updatedCustomer.getName());
        }
        else if(updatedCustomer.getEmail() != null){
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        else if(updatedCustomer.getPhoneNumber() != 0){
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        }
        else if(updatedCustomer.getAddress() != null){
            existingCustomer.setAddress(updatedCustomer.getAddress());
        }

        return existingCustomer;

    }

}












//public Customer createCustomer(Customer customer){
//
//    // one customer can create only one cart at a time not more than one
//    if(customer.getCrt() != null && !(customer.getCrt().size() > 1)) {
//
//        Cart cart = customer.getCrt().get(0);       //Retrieves the first cart from the customer's list of carts.
//
//
//    }
//
//    return customerRepository.save(customer);
//}









//System.out.println("---------------------------");
//            System.out.println(cart);
//
//            logger.info("---------------------------");
//            logger.info("Cart: {}", cart);  // Use a template for better formatting


//List<Cart> cartList = customer.getCrt();
//for (Cart cart : cartList) {
//        System.out.println(cart.getId()); // Prints the ID of each cart in the list
//        }
