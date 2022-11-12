package com.banking.banking.service;

import com.banking.banking.model.Bill;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public ResponseEntity<Iterable<Bill>> getAllBills() {
        Iterable<Bill> bills = billRepository.findAll();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    public ResponseEntity<?> getBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    /*public void verifyBill(Long billId) {
        Bill bill= BillRepository.findById(billId).orElse(null);
    }*/
    public void createBill(Bill bill) {
        bill=billRepository.save(bill);
    }
    public void updateBill(Bill bill, Long billId) {
        billRepository.save(bill);
    }

    public ResponseEntity<?> deleteBill(Long billId) {
        billRepository.deleteById(billId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

