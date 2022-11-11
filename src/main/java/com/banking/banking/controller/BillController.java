package com.banking.banking.controller;


import com.banking.banking.model.Bill;
import com.banking.banking.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Iterable<Bill>> getAllBillForAccount() {
        return billService.getAllBills();
    }
    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<Iterable<Bill>> getAllBillForCustomer(){
        return billService.getAllBills();
    }

}
