package com.mib.configuration.bprconfigurationsvc.repositories;

import com.mib.configuration.bprconfigurationsvc.entities.PromoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PromoRepo extends CrudRepository<PromoEntity, Long> {

    @Query("select p from PromoEntity p ")
    Page<PromoEntity> searchList(Pageable pageable);
}
