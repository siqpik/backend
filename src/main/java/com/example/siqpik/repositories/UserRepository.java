package com.example.siqpik.repositories;

import com.example.siqpik.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String name);
    List<User> findUsersByUserNameContainingOrNameContaining(String userName, String Name);
    Boolean existsByUserName(String userName);
}
