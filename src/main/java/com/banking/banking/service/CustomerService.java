package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Customer;
import com.banking.banking.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> createCustomer (Customer customer){
        try {
            Customer result = customerRepository.save(customer);
            return ResponseHandler.generateResponse( HttpStatus.OK, "Successfully added data!", result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse( HttpStatus.MULTI_STATUS,e.getMessage(), null);
        }

    }

    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        Iterable<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<?> getCustomerById(Long depositId) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(depositId).orElse(null);
        if (customer == null){
            throw new ResourceNotFoundException("Error fetching Customer");
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    public void updateCustomer(Customer customer, Long customerId){
        Customer c = customerRepository.save(customer);
    }

    public ResponseEntity<?> deleteCustomerById(Long customerId){
        customerRepository.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public Iterable<Customer> getCustomerByAccountId(Long accountId){
//      //  return customerRepository.findCustomerByAccountId(accountId);
//    }
public ResponseEntity<?> getCustomerByAccountId(Long accountId){
    Customer customer = customerRepository.findById(accountId).orElse(null);
    return new ResponseEntity<>(customer, HttpStatus.OK);
}
}
