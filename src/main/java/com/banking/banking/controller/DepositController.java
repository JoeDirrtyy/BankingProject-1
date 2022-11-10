package com.banking.banking.controller;

import com.banking.banking.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;
}
