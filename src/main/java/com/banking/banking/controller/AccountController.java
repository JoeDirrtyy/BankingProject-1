package com.banking.banking.controller;

import com.banking.banking.model.Account;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable(value = "customerId") Long customerId, @Validated @RequestBody Account account){
        return  accountService.createAccount(customerId,account);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountByAccountId(@PathVariable Long accountId) {
        return accountService.getAccountByAccountId(accountId);
    }


    @PutMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> updateAccount(@PathVariable(value = "customerId") Long customerId, @Validated @RequestBody Account account){
        return  accountService.updateAccount(customerId,account);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return accountService.deleteAccount(accountId);
    }

@GetMapping("/customers/{customerId}/accounts")
public ResponseEntity<?> getAccountsByCustomerId(@PathVariable Long customerId) {
return accountService.getAllAccountsByCustomerId(customerId);

}
}
