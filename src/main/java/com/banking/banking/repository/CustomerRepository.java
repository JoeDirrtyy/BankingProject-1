package com.banking.banking.repository;

import com.banking.banking.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  //  Iterable<Customer> findCustomerByAccountId(Long accountId);
}
