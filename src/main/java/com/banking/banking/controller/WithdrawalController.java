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



    @GetMapping("/accounts/{payeeId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawalsByAccountId(@PathVariable Long payeeId) {
        return withdrawalService.getAllDepositsByAccountId(payeeId);
    }

    @PostMapping("/accounts/{payerId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@Valid @RequestBody Withdrawal withdrawal, @PathVariable Long payerId) {
        return withdrawalService.createWithdrawal(withdrawal, payerId);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {
        return withdrawalService.getWithdrawalById(withdrawalId);
    }

    @PutMapping("/accounts/{payerId}/withdrawals")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long payerId) {
       return withdrawalService.updateWithdrawal(withdrawal, payerId);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawalById(@PathVariable Long withdrawalId) {
        return withdrawalService.deleteWithdrawalById(withdrawalId);
    }


}
