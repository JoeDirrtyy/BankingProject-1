package com.banking.banking.repository;

import com.banking.banking.model.Account;
import com.banking.banking.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Iterable<Account> getAllAccountsByCustomerId(Long customerId);

}
