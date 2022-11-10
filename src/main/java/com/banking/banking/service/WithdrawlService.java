package com.banking.banking.service;

import com.banking.banking.repository.WithDrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawlService {
    @Autowired
    private WithDrawlRepository withDrawlRepository;
}
