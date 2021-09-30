package com.mib.customer.bprcustomersvc.repositories;

import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {
}
