package com.banking.banking.controller;

import com.banking.banking.model.Withdrawal;
import com.banking.banking.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;



    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Iterable<Withdrawal>> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public void createWithdrawal(@Valid @RequestBody Withdrawal withdrawal) {
        withdrawalService.createWithdrawal(withdrawal);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {
        return withdrawalService.getWithdrawalById(withdrawalId);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public void updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long withdrawalId) {
        withdrawalService.updateWithdrawal(withdrawal, withdrawalId);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawalById(@PathVariable Long withdrawalId) {
        return withdrawalService.deleteWithdrawalById(withdrawalId);
    }


}
