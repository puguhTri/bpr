package com.mib.configuration.bprconfigurationsvc.repositories;

import com.mib.configuration.bprconfigurationsvc.entities.MediaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepo extends CrudRepository<MediaEntity, Long> {

    Optional<MediaEntity> findByFileId(String fileId);
}
