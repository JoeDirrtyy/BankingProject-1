package com.banking.banking.repository;

import com.banking.banking.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    Iterable<Bill> findBillByAccountId(Long accountId);

    @Query(value = "SELECT id FROM account WHERE customer_id = ?1", nativeQuery = true)
    Iterable<Long> getAccountIdThatMatchesCustomerId(Long customer_id);

    @Query(value = "SELECT * FROM bill WHERE account_id = ?1", nativeQuery = true)
    Iterable<Bill> getAllBillsByCustomerId(Iterable<Long> customerId);

}



