package com.example.spring.security.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.spring.security.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUserRepository extends ArangoRepository<User,String> {
    Optional<User> findByUsername(String userName);
}
