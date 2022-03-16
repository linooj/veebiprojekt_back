package com.ITI0302._happy.remake.repository;

import java.util.Optional;

import com.ITI0302._happy.remake.models.ERole;
import com.ITI0302._happy.remake.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
