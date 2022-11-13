package com.banking.banking.controller;

import com.banking.banking.model.Customer;
import com.banking.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customers")
    public ResponseEntity<?> createCustomer ( @Valid @RequestBody Customer customers){
        return customerService.createCustomer(customers);

    }

    @GetMapping(value = "/customers")
    public ResponseEntity<?> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id)  {
        return customerService.getCustomerById(id);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }

    @GetMapping(value = "account/{accountId}/customers")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId){
        return customerService.getCustomerByAccountId(accountId);
    }
}
