package com.banking.banking.service;

import com.banking.banking.model.Bill;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.BillRepository;
import com.banking.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public ResponseEntity<?> getBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
    public ResponseEntity<Iterable<Bill>> getAllBills(){
        Iterable<Bill> bills = billRepository.findAll();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
