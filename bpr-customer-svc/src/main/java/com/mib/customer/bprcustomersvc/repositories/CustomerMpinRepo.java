package com.mib.customer.bprcustomersvc.repositories;

import com.mib.customer.bprcustomersvc.entities.CustomerEntity;
import com.mib.customer.bprcustomersvc.entities.CustomerMpinEntity;
import com.mib.customer.bprcustomersvc.entities.CustomerOtpEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerMpinRepo extends CrudRepository<CustomerMpinEntity, Long> {

    Optional<CustomerMpinEntity> findByCustomer(CustomerEntity customer);

}
