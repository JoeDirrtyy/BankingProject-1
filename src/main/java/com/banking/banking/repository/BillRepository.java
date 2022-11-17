package com.banking.banking.repository;

import com.banking.banking.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    Iterable<Bill> findBillByAccountId(Long accountId);

    @Query(value = "SELECT * FROM Bill WHERE account_id = ?1", nativeQuery = true)
    Iterable<Bill> getAllBillsByAccountId(Long accountId);

}



