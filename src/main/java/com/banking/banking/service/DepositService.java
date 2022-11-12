package com.banking.banking.service;

import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    static Logger logger = LoggerFactory.getLogger(DepositService.class);



    public void createDeposit(Deposit deposit) throws ResourceNotFoundException{
        depositRepository.save(deposit);
        if (deposit.getPayee_id() == null){
            throw new ResourceNotFoundException( "Error creating Deposit");
        }
    }

//    public ResponseEntity<List<Deposit>> getAllDeposits() {
//        if (List.of().isEmpty())
//        if (depositRepository.findAll().equals(null))
//        return new ResponseEntity<>(deposits, HttpStatus.OK);
//    }

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
