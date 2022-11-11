package com.banking.banking.service;

import com.banking.banking.model.Bill;
import com.banking.banking.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

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
