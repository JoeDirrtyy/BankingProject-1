package com.banking.banking.repository;

import com.banking.banking.model.Account;
import com.banking.banking.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {
    Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long withdrawalId);

}
