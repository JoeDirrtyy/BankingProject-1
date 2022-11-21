package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;



    static Logger logger = LoggerFactory.getLogger(DepositService.class);



    public ResponseEntity<?> createDeposit(Deposit deposit, Long accountId) {
        // creating for a deposit for the account id
        // setting the payee id to the account
        //if the find by id is equal to null throw an exception
        // if the deposit is less than zero throw an exception
// else add the account balance and deposit amount and set the account balance to transaction
        // then return response handler
        Account account = accountRepository.findById(accountId).orElse(null);
        deposit.setAccount(account);
        if (account == null){
            throw new ResourceNotFoundException( "Error creating Deposit");
        } else if (deposit.getAmount() < 0) {
            throw new ResourceNotFoundException("Cannot make negative deposit");
        } else {
            Double accountBalance = account.getBalance();
            Double depositAmount = deposit.getAmount();

            Double transaction = depositAmount + accountBalance;

            account.setBalance(transaction);

            depositRepository.save(deposit);
            return ResponseHandler.generateResponse("Successfully created deposit!", HttpStatus.OK, account);
        }
    }

    public ResponseEntity<?> getAllDepositsByTheAccountId(Long accountId){
       List<Deposit> deposits = (List<Deposit>) depositRepository.getAllDepositsByAccountId(accountId);

//         find the deposit by id
//         if the deposit id is equal to null
//         throw an exception
//         account id is already in endpoint
        if (deposits.isEmpty()){
            throw new ResourceNotFoundException("error fetching deposit");
        }
        return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, deposits);
    }

    public ResponseEntity<?> getDepositById(Long depositId) throws ResourceNotFoundException {
        // find the deposit by id
        // if the account id is equal to null
        // throw an exception
        Deposit deposit1 = depositRepository.findById(depositId).orElse(null);
        if (deposit1 == null){
            throw new ResourceNotFoundException("error fetching deposit");
        }
        return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, deposit1);
    }

    public ResponseEntity<?> updateDeposit(Deposit deposit, Long depositId) {

        // updating a deposit for the account id
        // setting the payee id to the account
        //if the find by id is equal to null throw an exception
        // if the deposit is less than zero throw an exception
// else add the account balance and deposit amount and set the account balance to transaction
        // then return response handler
        Account account = accountRepository.findById(deposit.getId()).orElse(null);
        deposit.setAccount(account);
        if (account == null){
            throw new ResourceNotFoundException( "Error updating Deposit");
        } else if (deposit.getAmount() < 0) {
            throw new ResourceNotFoundException("Cannot make negative deposit");
        } else {
            Double oldDepositAmount = depositRepository.findById(depositId).get().getAmount();

            Double accountBalance = account.getBalance();

            Double oldBalance = accountBalance - oldDepositAmount;
            account.setBalance(oldBalance);

            Double depositAmount = deposit.getAmount();

            Double transaction = oldBalance + depositAmount;
            account.setBalance(transaction);

            depositRepository.save(deposit);
            return ResponseHandler.generateResponse("Successfully updated deposit!", HttpStatus.OK, account);
        }
    }
    public ResponseEntity<?> deleteDepositById(Long depositId)  {

        // find the account by id
        // if the account id is equal to null
        // throw an exception
        // else delete the deposit
        Deposit c = depositRepository.findById(depositId).orElse(null);
        if (c == null) {
            throw new ResourceNotFoundException("error deleting deposit");
        }else {
            depositRepository.deleteById(depositId);
        }
        return ResponseHandler.generateResponseNoObj("Deposit deleted", HttpStatus.OK);
    }

}
