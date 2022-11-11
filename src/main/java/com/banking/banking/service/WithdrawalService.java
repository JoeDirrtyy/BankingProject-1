package com.banking.banking.service;

import com.banking.banking.model.Withdrawal;
import com.banking.banking.repository.WithDrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalService {
    @Autowired
    private WithDrawlRepository withDrawlRepository;

    public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        return withDrawlRepository.save(withdrawal);
    }



    public ResponseEntity<Iterable<Withdrawal>> getAllwithdrawals() {
        Iterable<Withdrawal> categories = withDrawlRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawal_id) {
        withDrawlRepository.save(withdrawal);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> deleteWithdrawal(Long withdrawal_id) {
        withDrawlRepository.deleteById(withdrawal_id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
