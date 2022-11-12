package com.banking.banking.service;

import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

    public ResponseEntity<?> getDepositById(Long depositId) throws ResourceNotFoundException {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        if (deposit == null){
            throw new ResourceNotFoundException("Error getting deposit by id");
        }
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    public void updateDeposit(Deposit deposit, Long depositId) throws ResourceNotFoundException{
        Deposit c = depositRepository.save(deposit);
        if (depositId == null){
            throw new ResourceNotFoundException("Error updating deposit");
        }
    }

    public ResponseEntity<?> deleteDepositById(Long depositId) throws ResourceNotFoundException {
        if (depositRepository.findById(depositId).isEmpty()){
            throw new ResourceNotFoundException("Error deleting deposit");
        }
        depositRepository.deleteById(depositId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
