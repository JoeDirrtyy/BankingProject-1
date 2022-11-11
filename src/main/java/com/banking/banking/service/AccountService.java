package com.banking.banking.service;

import com.banking.banking.model.Account;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> createAccount(Long customerId, Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            return accountRepository.save(account);
        });
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public ResponseEntity<Iterable<Account>> getAllAccounts(){
        Iterable<Account> allAccounts = accountRepository.findAll();
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    public Optional<Account> updateAccount(Long customerId, Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            return accountRepository.save(account);
        });
    }

    public  Iterable<Account> findAccountByName(String query){
        return accountRepository.findAccountByName(query);
    }

    public  Iterable<Account> findByAccountId(Long accountId){
        return accountRepository.findByAccountId(accountId);
    }


}
