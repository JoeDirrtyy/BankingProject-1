package com.banking.banking.service;

import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Withdrawal;
import com.banking.banking.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    


    public void createWithdrawal(Withdrawal withdrawal) {
        withdrawalRepository.save(withdrawal);
    }

    public ResponseEntity<Iterable<Withdrawal>> getAllWithdrawals() {
        Iterable<Withdrawal> withdrawals = withdrawalRepository.findAll();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) throws ResourceNotFoundException {
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (withdrawal == null){
            throw new ResourceNotFoundException("Error getting a withdrawal");
        }
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    public void updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) throws ResourceNotFoundException{
        withdrawal = withdrawalRepository.save(withdrawal);
        if (withdrawalId == null){
            throw new ResourceNotFoundException("Error updating withdrawal");
        }
    }

    public ResponseEntity<?> deleteWithdrawalById(Long withdrawalId) {
        if (withdrawalRepository.findById(withdrawalId).isEmpty()){
            throw new ResourceNotFoundException("Error deleting withdrawal");
        }
        withdrawalRepository.deleteById(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
