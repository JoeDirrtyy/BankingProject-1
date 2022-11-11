package com.banking.banking.controller;

import com.banking.banking.model.Customer;
import com.banking.banking.repository.CustomerRepository;
import com.banking.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public void createCustomer (@Valid @RequestBody Customer customer){
        customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }

    @GetMapping("accounts/{accountsId}/customers")
    public Iterable<Customer> getAllCustomerByAccountId(@PathVariable Long accountsId){
        return customerService.getCustomerByAccountId(accountsId);
    }
}
