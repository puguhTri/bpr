package com.mib.auth.bprauthsvc.repositories;

import com.mib.auth.bprauthsvc.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUserId(String userId);
}
