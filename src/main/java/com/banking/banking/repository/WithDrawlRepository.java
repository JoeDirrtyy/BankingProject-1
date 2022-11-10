package com.banking.banking.repository;

import com.banking.banking.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithDrawlRepository extends CrudRepository<Withdrawal, Long> {
}
