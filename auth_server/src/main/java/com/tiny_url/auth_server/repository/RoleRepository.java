package com.tiny_url.auth_server.repository;

import com.tiny_url.auth_server.models.ERole;
import com.tiny_url.auth_server.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}