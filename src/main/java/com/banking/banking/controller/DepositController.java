package com.banking.banking.controller;

import com.banking.banking.model.Deposit;
import com.banking.banking.service.DepositService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;





//    @GetMapping("/accounts/{accountId}/deposits")
//    public ResponseEntity<Iterable<Deposit>> getAllDeposits() {
//        return depositService.getAllDeposits();
//    }

    @PostMapping( "/accounts/{accountId}/deposits")
    public void createDeposit(@Valid @RequestBody Deposit deposit) {
        depositService.createDeposit(deposit);
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDepositbyId(@PathVariable Long depositId) {
        return depositService.getDepositById(depositId);
    }

    @PutMapping("/deposits/{depositId}")
    public void updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId) {
        depositService.updateDeposit(deposit, depositId);
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDepositById(@PathVariable Long depositId) {
        return depositService.deleteDepositById(depositId);
    }


}
