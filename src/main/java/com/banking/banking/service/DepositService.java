package com.banking.banking.service;

import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;



    public void createDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }

    public ResponseEntity<Iterable<Deposit>> getAllDeposits() {
        Iterable<Deposit> deposits = depositRepository.findAll();
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }

    public ResponseEntity<?> getDepositById(Long depositId) {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    public void updateDeposit(Deposit deposit, Long depositId) {
        Deposit c = depositRepository.save(deposit);
    }

    public ResponseEntity<?> deleteDepositById(Long depositId) {
        depositRepository.deleteById(depositId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
