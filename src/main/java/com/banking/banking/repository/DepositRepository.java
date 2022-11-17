package com.banking.banking.repository;

import com.banking.banking.model.Account;
import com.banking.banking.model.Deposit;
import com.banking.banking.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    Iterable<Deposit> getAllDepositsByAccountId(Long accountId);

}
