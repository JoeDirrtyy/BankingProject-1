package com.banking.banking.controller;

import com.banking.banking.model.Bill;
import com.banking.banking.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.banking.banking.model.Bill;
import com.banking.banking.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillForAccount() {
        return billService.getAllBills();
    }
    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillForCustomer(){
        return billService.getAllBills();
    }

    @PostMapping("/accounts/{accountId}/bills")
    public void createBill(@Valid @RequestBody Bill bill) {
        billService.createBill(bill);
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId) {
       return billService.updateBill(bill, billId);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        return billService.deleteBill(billId);
    }
}

