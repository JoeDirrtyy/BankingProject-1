package com.banking.banking.controller;

import com.banking.banking.ResponseHandler.ResponseHandler;
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
import java.util.List;

@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillForAccount(@PathVariable Long accountId) {
        return billService.getAllBillsforAccount(accountId);
    }
    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillForCustomer(@PathVariable Long customerId){
        return billService.getAllBillsforCustomer(customerId);

        //  List<Bill> bills =  billService.getAllBillsforCustomer(customerId);
        //  return ResponseHandler.generateResponse("Successfully retrieved bill data!", HttpStatus.OK, bills);

    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@PathVariable Long accountId, @Valid @RequestBody Bill bills) {
        return billService.createBill(accountId, bills);
    }

    @PutMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> updateBill(@RequestBody Bill bills, @PathVariable Long accountId) {
       return billService.updateBill(bills, accountId);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        return billService.deleteBill(billId);
    }
}

