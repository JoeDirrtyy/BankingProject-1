package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;


    public ResponseEntity<?> createAccount(Long customerId, Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            accountRepository.save(account);
            return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, account);


        }).orElseThrow(() -> new ResourceNotFoundException("Customer ID not found"));

    }

    public ResponseEntity<?> getAccountByAccountId(Long accountId){
        Optional<Account> a = accountRepository.findById(accountId);
        if(a.isEmpty()){
           throw new ResourceNotFoundException("Could not retrieve account ID");
        }
        return  ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, a);
    }

    public ResponseEntity<?> deleteAccount(Long accountId){
        Optional<Account> a = accountRepository.findById(accountId);
        if (a.isEmpty()) {
            throw new ResourceNotFoundException("Error deleting account");
        }else {
            accountRepository.deleteById(accountId);
        }
        return ResponseHandler.generateResponseNoObj("Account has been deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getAllAccounts(){
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        if(accounts.isEmpty()){
            throw new ResourceNotFoundException("Error fetching accounts");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, accounts);
    }

//    public ResponseEntity<?> getAccountById(Long accountId) throws ResourceNotFoundException {
//        Account account1 = accountRepository.findById(accountId).orElse(null);
//        if (account1 == null){
//            throw new ResourceNotFoundException("Error fetching Account");
//        }
//        return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, account1);
//    }

    public ResponseEntity<?> updateAccount(Long customerId, Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            accountRepository.save(account);
            return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, account);


        }).orElseThrow(() -> new ResourceNotFoundException("Customer ID not found"));

    }

}
