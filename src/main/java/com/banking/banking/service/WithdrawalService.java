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
    


    public void updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {

        Account account = accountService.getAccountByAccountId(withdrawal.getPayer_id()).orElse(null);
        Double oldWithdrawalAmount = withdrawalRepository.findById(withdrawalId).get().getAmount();
        Double accountBalance = account.getBalance();
        Double oldBalance = accountBalance + oldWithdrawalAmount;
        account.setBalance(oldBalance);
        Double depositAmount = withdrawal.getAmount();
        Double transaction = oldBalance - depositAmount;
        account.setBalance(transaction);
        withdrawalRepository.save(withdrawal);

    }

    public ResponseEntity<?> getAllWithdrawals() {
        List<Withdrawal> withdrawals = (List<Withdrawal>) withdrawalRepository.findAll();
        if(withdrawals.isEmpty()){
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

    public ResponseEntity<?> createWithdrawall(Withdrawal withdrawal, Long accountId) {
        Optional<Account> account = accountService.getAccountByAccountId(accountId);

        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();
        Double total = accountBalance - withdrawalAmount;
        account.get().setBalance(total);

        if (accountId == null){
            throw new ResourceNotFoundException("error updating withdrawal cant find account");
        } else if (account.get().getBalance() <= 0) {
            throw new ResourceNotFoundException("error creating withdrawal must be more than find zero");
        } else {
            withdrawalRepository.save(withdrawal);
        }
        return ResponseHandler.generateResponse("Updated", HttpStatus.OK, account);
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
