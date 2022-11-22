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



    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal, Long accountId) {
        // creating for a withdrawal for the account id
        // finding the account by the id
        // setting the withdrawal to that account id
        //if the find by id is equal to null throw an exception
        // if the with drawl is less than zero throw an exception
        // if the with drawl amount is more than the account balance throw an exception
        Account account = accountRepository.findById(accountId).orElse(null);
        withdrawal.setAccount(account);

        if (account == null){
            throw new ResourceNotFoundException( "Error creating withdrawal");
        } else if (withdrawal.getAmount() <= 0) {
            throw new ResourceNotFoundException("Cannot make negative withdrawal");
        } else if (account.getBalance() <= withdrawal.getAmount()) {
            throw new ResourceNotFoundException("Cannot make Withdrawal larger that account balance");
        } else {
            Double accountBalance = account.getBalance();
            // getting the balance amount

            Double withdrawalAmount = withdrawal.getAmount();
            // getting the withdrawal amount
            Double transaction = accountBalance - withdrawalAmount;
            // subtracting the account balance and the withdrawal amount
            account.setBalance(transaction);
// setting the balance to the account balance - withdrawal amount
            withdrawalRepository.save(withdrawal);
            return ResponseHandler.generateResponse("Successfully created Withdrawal!", HttpStatus.OK, account);
        }
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {

        // creating for a withdrawal for the account id
        //finding the account withdrawal id
        // setting the account id to the withdrawal
        //if the find by id is equal to null throw an exception
        // if the with drawl is less than zero throw an exception
        // if the with drawl amount is more than the account balance throw an exception

        Account account = accountRepository.findById(withdrawal.getId()).orElse(null);
        withdrawal.setAccount(account);
        if (account == null){
            throw new ResourceNotFoundException( "Error updating withdrawal");
        } else if (withdrawal.getAmount() < 0) {
            throw new ResourceNotFoundException("Cannot make negative withdrawal");
        } else if (account.getBalance() <= withdrawal.getAmount()) {
            throw new ResourceNotFoundException("Cannot make Withdrawal larger that account balance");
        }else {

            Double oldWithdrawal = withdrawalRepository.findById(withdrawalId).get().getAmount();
            // getting the withdrawal amount
            Double balance = account.getBalance();
            // getting the balance
            Double oldBalance = balance + oldWithdrawal;
            // getting the old balance before the withdrawal
            account.setBalance(oldBalance);
            // setting the old balance

            Double withdrawalAmount = withdrawal.getAmount();
            // getting amount of withdrawal

            account.setBalance(oldBalance - withdrawalAmount);
// subtracting the old balance and the new withdrawal amount
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

    public ResponseEntity<?> getAllWithdrawalsByAccountId(Long accountId){
        List<Withdrawal> withdrawals = (List<Withdrawal>) withdrawalRepository.getAllWithdrawalsByAccountId(accountId);

//         find the deposit by id
//         if the deposit id is equal to null
//         throw an exception
//         account id is already in endpoint
        if (withdrawals.isEmpty()){
            throw new ResourceNotFoundException("error fetching withdrawal");
        }
        return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, withdrawals);
    }



    public ResponseEntity<?> deleteWithdrawalById(Long withdrawalId) {
        // find the withdrawal id
        // if the withdrawal is equal to null
        // throw exception
        // else delete withdrawal id
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (withdrawal == null) {
            throw new ResourceNotFoundException("error deleting withdrawal");
        }else {
            withdrawalRepository.deleteById(withdrawalId);
        }
        return ResponseHandler.generateResponseNoObj("Withdrawal deleted", HttpStatus.OK);
    }


}
