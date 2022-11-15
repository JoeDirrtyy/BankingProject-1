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

import java.util.List;
import java.util.Optional;


@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountService accountService;



    static Logger logger = LoggerFactory.getLogger(DepositService.class);



    public ResponseEntity<?> createDeposit(Deposit deposit, Long accountId) {
        Optional<Account> account = accountService.getAccountByAccountId(accountId);

        Double accountBalance = account.get().getBalance();
        Double depositAmount = deposit.getAmount();

        Double transaction = depositAmount + accountBalance;

        account.get().setBalance(transaction);

        depositRepository.save(deposit);
        if (deposit.getPayee_id() == null){
            throw new ResourceNotFoundException( "Error creating Deposit");
        }
        return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, account);

    }

    public ResponseEntity<?> getAllDeposits(){
        List<Deposit> deposits = (List<Deposit>) depositRepository.findAll();
        if(deposits.isEmpty()){
            throw new ResourceNotFoundException("error fetching deposits");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, deposits);
    }

    public ResponseEntity<?> getDepositById(Long depositId) throws ResourceNotFoundException {
        Deposit deposit1 = depositRepository.findById(depositId).orElse(null);
        if (deposit1 == null){
            throw new ResourceNotFoundException("error fetching deposit");
        }
        return ResponseHandler.generateResponse("Successfully retrieved deposit data!", HttpStatus.OK, deposit1);
    }

    public ResponseEntity<?> updateDeposit(Deposit deposit, Long depositId) {
        Account account = accountService.getAccountByAccountId(deposit.getPayee_id()).orElse(null);

        Double oldDepositAmount = depositRepository.findById(depositId).get().getAmount();

        Double accountBalance = account.getBalance();

        Double oldBalance = accountBalance - oldDepositAmount;
        account.setBalance(oldBalance);

        Double depositAmount = deposit.getAmount();

        Double transaction = oldBalance + depositAmount;
        account.setBalance(transaction);

        depositRepository.save(deposit);

        return ResponseHandler.generateResponse("Updated", HttpStatus.OK, account);
    }

    public ResponseEntity<?> deleteDepositById(Long depositId)  {
        Deposit c = depositRepository.findById(depositId).orElse(null);
        if (c == null) {
            throw new ResourceNotFoundException("error deleting deposit");
        }else {
            depositRepository.deleteById(depositId);
        }
        return ResponseHandler.generateResponseNoObj("Deposit deleted", HttpStatus.OK);
    }





}
