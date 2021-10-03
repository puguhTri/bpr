package com.mib.customer.bprcustomersvc.repositories;

import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByAccountNumber(String accountNumber);

    Optional<CustomerEntity> findByCustomerId(UUID customerId);

    Optional<CustomerEntity> findByEmail(String email);


}
