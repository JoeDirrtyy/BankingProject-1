package com.banking.banking.controller;

import com.banking.banking.model.Deposit;
import com.banking.banking.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;


    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Iterable<Deposit>> getAlldeposits() {
        return depositService.getAlldeposits();
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public Deposit createDeposit(Deposit deposit) {
        return depositService.createDeposit(deposit);
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDepositbyId(@PathVariable Long id) {
        return depositService.getDepositById(id);
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(Deposit deposit, Long deposit_id) {
        return depositService.updateDeposit(deposit, deposit_id);
    }
}
