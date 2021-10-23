package com.mib.customer.bprcustomersvc.repositories;

import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByAccountNumber(String accountNumber);

    Optional<CustomerEntity> findByCustomerId(UUID customerId);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByUsername(String username);

    Page<CustomerEntity> findAll(Pageable pageable);


}
