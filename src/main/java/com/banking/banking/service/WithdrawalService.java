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
        // creating for a withdrawal for the account id
        // setting the account id to the account
        //if the find by id is equal to null throw an exception
        // if the with drawl is less than zero throw an exception
        // if the with drawl amount is more than the account balance throw an exception
// else subtract the account balance and withdrawal amount and set the account balance to transaction
        // save the withdrawal
        // then return response handler
      // Optional<Account> account  = accountRepository.findById(withdrawalId)
        Account account = accountRepository.findById(withdrawalId).orElse(null);
        withdrawal.setAccount(account);

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
            return ResponseHandler.generateResponse("Successfully created Withdrawal!", HttpStatus.OK, account);
        }
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {

        // creating for a withdrawal for the account id
        // setting the payee id to the account
        //if the find by id is equal to null throw an exception
        // if the with drawl is less than zero throw an exception
        // if the with drawl amount is more than the account balance throw an exception
// else subtract the account balance and withdrawal amount and set the account balance to transaction
        // save the withdrawal
        // then return response handler
        Account account = accountRepository.findById(withdrawal.getId()).orElse(null);
        withdrawal.setAccount(account);
        if (account == null){
            throw new ResourceNotFoundException( "Error creating withdrawal");
        } else if (withdrawal.getAmount() < 0) {
            throw new ResourceNotFoundException("Cannot make negative withdrawal");
        } else if (account.getBalance() <= withdrawal.getAmount()) {
            throw new ResourceNotFoundException("Cannot make Withdrawal larger that account balance");
        }else {

            Double oldWithdrawalAmount = withdrawalRepository.findById(withdrawalId).get().getAmount();

            Double accountBalance = account.getBalance();

            Double oldBalance = accountBalance + oldWithdrawalAmount;
            account.setBalance(oldBalance);

            Double withdrawalAmount = withdrawal.getAmount();

            Double transaction = oldBalance - withdrawalAmount;
            account.setBalance(transaction);

            withdrawalRepository.save(withdrawal);
            return ResponseHandler.generateResponse("Successfully updated Withdrawal!", HttpStatus.OK, account);
        }
    }


    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) {
        // get the withdraw by id
        // if the withdrawal id is equal to null
        // throw an exception
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (withdrawal == null){
            throw new ResourceNotFoundException("Error getting a withdrawal");
        }
        return ResponseHandler.generateResponse("Successfully retrieved withdrawal data!", HttpStatus.OK, withdrawal);
    }

    public ResponseEntity<?> getAllDepositsByAccountId(Long accountId){
        Iterable<Withdrawal> withdrawals = withdrawalRepository.getAllWithdrawalsByAccountId(accountId);

//         find the deposit by id
//         if the deposit id is equal to null
//         throw an exception
//         account id is already in endpoint
        if (withdrawals == null){
            throw new ResourceNotFoundException("error fetching deposit");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, withdrawals);
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
