package com.banking.banking.repository;

import com.banking.banking.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "select * from account where name LIKE concat('%',:query,'%')", nativeQuery = true)
    Iterable<Account> findAccountByName(String query);

    Iterable<Account> findByAccountId(Long accountId);
}
