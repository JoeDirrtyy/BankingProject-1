package com.banking.banking.service;

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

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) {
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    public void updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {
        withdrawal = withdrawalRepository.save(withdrawal);
    }

    public ResponseEntity<?> deleteWithdrawalById(Long withdrawalId) {
        withdrawalRepository.deleteById(withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
