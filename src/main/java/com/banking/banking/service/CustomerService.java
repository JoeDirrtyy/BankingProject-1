package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.model.Customer;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> createCustomer (Customer customer) {
        Customer customer1 = customerRepository.save(customer);
        if (customer1.getAddress() == null) {
            throw new ResourceNotFoundException("error");
        } else if (customer1.getFirst_name() == null) {
            throw new ResourceNotFoundException("error");
        } else if (customer1.getLast_name() == null) {
            throw new ResourceNotFoundException("error");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, customer1);
        }



//Shawn
    //Throat punch
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        if(customers.isEmpty()){
            throw new ResourceNotFoundException("error fetching customers");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, customers);
    }

    public ResponseEntity<?> getCustomerById(Long customerID) {
        Optional<Customer> c = customerRepository.findById(customerID);
        if (c.isEmpty()){
            throw new ResourceNotFoundException("error fetching account");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customer data!", HttpStatus.OK, c);
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId){
        Optional<Customer> c = customerRepository.findById(customerId);
        if (c.isEmpty()){
            throw new ResourceNotFoundException("error updating account");
        }else {
            customerRepository.save(customer);
        }
        return ResponseHandler.generateResponse("Updated", HttpStatus.OK, c);
    }
    //broken

    public ResponseEntity<?> deleteCustomerById(Long customerId) {
        Optional<Customer> c = customerRepository.findById(customerId);
        if (c.isEmpty()) {
            throw new ResourceNotFoundException("error deleting account");
        }else {
            customerRepository.deleteById(customerId);
        }
            return ResponseHandler.generateResponseNoObj("Customer deleted", HttpStatus.OK);
        }


    public ResponseEntity<?> getCustomerByAccountId(Long accountId){
        Iterable<Account> customerId = accountRepository.getAllAccountsByCustomerId(accountId);

        if (customerId == null){
            throw new ResourceNotFoundException("error fetching customer");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customer data!", HttpStatus.OK, customerId);
    }
}
