package com.mib.configuration.bprconfigurationsvc.repositories;

import com.mib.configuration.bprconfigurationsvc.entities.CustomersCareEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomersCareRepo extends CrudRepository<CustomersCareEntity, Long> {

    @Query("select c from CustomersCareEntity c")
    Page<CustomersCareEntity> searchList(Pageable pageable);

}
