package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Account;
import com.banking.banking.model.Bill;
import com.banking.banking.model.Customer;
import com.banking.banking.model.Deposit;
import com.banking.banking.repository.AccountRepository;
import com.banking.banking.repository.BillRepository;
import com.banking.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    public ResponseEntity<?> getAllBillsforCustomer(Long customer_iD) {

        Iterable<Bill> bills = billRepository.getAllBillsByCustomerId(customer_iD);

        if (bills == null){
         throw new ResourceNotFoundException("Bills not found.");
        }
       return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, bills);
    }

    public ResponseEntity<?> getAllBillsforAccount(Long accountID) {
        Iterable<Bill> bills = billRepository.findBillByAccountId(accountID);
        if (bills == null){
            throw new ResourceNotFoundException("Bills not found.");
        }
        return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, bills);
    }

    public ResponseEntity<?> getBillById(Long billId) {
        Optional<Bill> a = billRepository.findById(billId);
        if (a.isEmpty()){
            throw new ResourceNotFoundException("Getting Bill by ID not found.");
        }
        return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, a);
    }

    public ResponseEntity<?> createBill(Long accountId, Bill bill) {
        accountRepository.findById(accountId).map(account -> {
            bill.setAccount(account);
            return billRepository.save(bill);
        }).orElseThrow(() -> new ResourceNotFoundException("couldn't create bill"));
        return ResponseHandler.generateResponse("Successfully created bill", HttpStatus.OK, bill);
    }
    public ResponseEntity<?> updateBill(Bill bill, Long accountId) {
        accountRepository.findById(accountId).map(account -> {
            bill.setAccount(account);
            return billRepository.save(bill);
        }).orElseThrow(() -> new ResourceNotFoundException("couldn't update bill"));
        return ResponseHandler.generateResponseNoObj(" bill modification completed", HttpStatus.OK);
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

