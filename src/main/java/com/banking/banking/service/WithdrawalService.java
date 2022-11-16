package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.model.Withdrawal;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private  AccountRepository accountRepository;



    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal, Long withdrawalId) {
        Account account = accountRepository.findById(withdrawalId).orElse(null);
        withdrawal.setPayer_id(account);

        if (account == null){
            throw new ResourceNotFoundException( "Error creating withdrawal");
        } else if (withdrawal.getAmount() <= 0) {
            throw new ResourceNotFoundException("Cannot make negative withdrawal");
        } else if (account.getBalance() <= withdrawal.getAmount()) {
            throw new ResourceNotFoundException("Cannot make Withdrawal larger that account balance");
        } else {
            Double accountBalance = account.getBalance();
            Double withdrawalAmount = withdrawal.getAmount();
            Double transaction = accountBalance - withdrawalAmount;

            account.setBalance(transaction);

            withdrawalRepository.save(withdrawal);
            return ResponseHandler.generateResponse("Successfully retrieved Withdrawal data!", HttpStatus.OK, account);
        }
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {
        Account account = accountRepository.findById(withdrawalId).orElse(null);
        withdrawal.setPayer_id(account);
        if (account == null){
            throw new ResourceNotFoundException( "Error creating withdrawal");
        } else if (withdrawal.getAmount() < 0) {
            throw new ResourceNotFoundException("Cannot make negative withdrawal");
        } else if (account.getBalance() <= withdrawal.getAmount()) {
            throw new ResourceNotFoundException("Cannot make Withdrawal larger that account balance");
        }else {
            Double accountBalance = account.getBalance();
            Double withdrawalAmount = withdrawal.getAmount();

            Double transaction = accountBalance - withdrawalAmount;

            account.setBalance(transaction);

            withdrawalRepository.save(withdrawal);
            return ResponseHandler.generateResponse("Successfully retrieved Withdrawal data!", HttpStatus.OK, account);
        }
    }

    public ResponseEntity<?> getAllWithdrawals(Long id) {
        Withdrawal withdrawals = withdrawalRepository.findById(id).orElse(null);
        if(withdrawals == null){
            throw new ResourceNotFoundException("error fetching customers");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, withdrawals);
    }

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) {
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (withdrawal == null){
            throw new ResourceNotFoundException("Error getting a withdrawal");
        }
        return ResponseHandler.generateResponse("Successfully retrieved withdrawal data!", HttpStatus.OK, withdrawal);
    }



    public ResponseEntity<?> deleteWithdrawalById(Long withdrawalId) {
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (withdrawal == null) {
            throw new ResourceNotFoundException("error deleting withdrawal");
        }else {
            withdrawalRepository.deleteById(withdrawalId);
        }
        return ResponseHandler.generateResponseNoObj("Withdrawal deleted", HttpStatus.OK);
    }


}
