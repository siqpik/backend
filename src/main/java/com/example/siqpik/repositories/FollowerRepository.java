package com.example.siqpik.repositories;

import com.example.siqpik.Admirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FollowerRepository extends JpaRepository<Admirer, Long> { }
