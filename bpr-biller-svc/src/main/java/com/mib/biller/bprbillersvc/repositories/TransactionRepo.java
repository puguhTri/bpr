package com.mib.biller.bprbillersvc.repositories;

import com.mib.biller.bprbillersvc.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findByCustomerIdAndCode(UUID customerId, UUID code);

}
