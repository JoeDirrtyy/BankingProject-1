package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Bill;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public ResponseEntity<?> getAllBills() {
        List<Bill> bills = (List<Bill>) billRepository.findAll();
        if (bills.isEmpty()){
         throw new ResourceNotFoundException("Bills not found.");
        }
        return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, bills);
    }

    public ResponseEntity<?> getBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        if (bill == null){
            throw new ResourceNotFoundException("Getting Bill by ID not found.");
        }
        return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, bill);
    }

    /*public void verifyBill(Long billId) {
        Bill bill= BillRepository.findById(billId).orElse(null);
    }*/
    public void createBill(Bill bill) {
        bill=billRepository.save(bill);
        if (bill.getAccount_id() == null){
            throw new ResourceNotFoundException("Create Bill not found.");
        }
    }
    public ResponseEntity<?> updateBill(Bill bill, Long billId) {
        Bill billc = billRepository.findById(billId).orElse(null);
        if (billc == null) {
            throw new ResourceNotFoundException("Update Bill not found.");
        }else billRepository.save(bill);
        return ResponseHandler.generateResponse("Successfully retrieved update data!", HttpStatus.OK, billc);
    }
    public ResponseEntity<?> deleteBill(Long billId) {
        Bill c = billRepository.findById(billId).orElse(null);
        if (c == null) {
            throw new ResourceNotFoundException("error deleting bill");
        }else {
            billRepository.deleteById(billId);
        }
        return ResponseHandler.generateResponseNoObj("bill deleted", HttpStatus.OK);
    }


}

