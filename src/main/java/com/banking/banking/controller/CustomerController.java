package com.banking.banking.controller;

import com.banking.banking.model.Customer;
import com.banking.banking.repository.CustomerRepository;
import com.banking.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public void createCustomer (Customer customer){
        customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId){
        customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId){
        return customerService.deleteCustomerById(customerId);
    }
}
