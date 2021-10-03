package com.mib.customer.bprcustomersvc.repositories;

import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import com.mib.customer.bprcustomersvc.entities.CustomerOtpEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerOtpRepo extends CrudRepository<CustomerOtpEntity, Long> {

    Optional<CustomerOtpEntity> findByCustomer(CustomerEntity customer);

}
