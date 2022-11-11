package com.banking.banking.service;

import com.banking.banking.model.Deposit;
import com.banking.banking.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    public Deposit createDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public ResponseEntity<Iterable<Deposit>> getAlldeposits() {
        Iterable<Deposit> categories = depositRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    public ResponseEntity<?> updateDeposit(Deposit deposit, Long deposit_id) {
        depositRepository.save(deposit);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> deleteDeposit(Long deposit_id) {
        depositRepository.deleteById(deposit_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getDepositById(Long deposit_id) {

        Deposit p = depositRepository.findById(deposit_id).orElse(null);
        return new ResponseEntity<>(p, HttpStatus.OK);

    }
}
