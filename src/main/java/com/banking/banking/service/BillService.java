package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.model.Bill;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.AccountRepository;
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

    @Autowired
    private AccountRepository accountRepository;

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
    public ResponseEntity<?> createBill(Long accountId, Bill bill) {
        accountRepository.findById(accountId).map(account -> {
            bill.setAccount(account);
            return billRepository.save(bill);
        }).orElseThrow(() -> new ResourceNotFoundException("couldn't create bill"));
        return ResponseHandler.generateResponse("Here is your bill", HttpStatus.OK, bill);
    }
    public ResponseEntity<?> updateBill(Bill bill, Long accountId) {
//        Bill billU = billRepository.findById(billId).orElse(null);
//        if(billU == null){
//            throw new ResourceNotFoundException("Couldn't update bill");
//        }else{
//            billRepository.save(bill);
//        }
//        return ResponseHandler.generateResponseNoObj("Bill updated", HttpStatus.OK);
        accountRepository.findById(accountId).map(account -> {
            bill.setAccount(account);
            return billRepository.save(bill);
        }).orElseThrow(() -> new ResourceNotFoundException("couldn't update bill"));
        return ResponseHandler.generateResponseNoObj("Here is your bill", HttpStatus.OK);
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

