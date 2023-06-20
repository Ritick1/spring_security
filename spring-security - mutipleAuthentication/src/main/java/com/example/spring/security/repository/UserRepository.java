package com.example.spring.security.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.spring.security.Domain.vertex.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ArangoRepository<User,String> {
    Optional<User> findByUsername(String userName);
}
