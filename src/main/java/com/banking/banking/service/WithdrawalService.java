package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.model.Withdrawal;
import com.banking.banking.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    


    public void createWithdrawal(Withdrawal withdrawal) {
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

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) throws ResourceNotFoundException{
        Withdrawal c = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (c == null){
            throw new ResourceNotFoundException("error updating deposit");
        }else {
            withdrawalRepository.save(withdrawal);
        }
        return ResponseHandler.generateResponse("Updated", HttpStatus.OK, c);
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
