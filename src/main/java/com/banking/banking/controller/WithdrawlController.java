package com.banking.banking.controller;

import com.banking.banking.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawlController {
    @Autowired
    private WithdrawalService withdrawlService;
}
