package com.banking.banking.service;

import com.banking.banking.model.Customer;
import com.banking.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer (Customer customer){
        customerRepository.save(customer);
    }

    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        Iterable<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<?> getCustomerById(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    public void updateCustomer(Customer customer, Long customerId){
        Customer c = customerRepository.save(customer);
    }

    public ResponseEntity<?> deleteCustomerById(Long customerId){
        customerRepository.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
