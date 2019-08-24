package com.example.siqpik.repositories;

import com.example.siqpik.domain.Admirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdmirerRepository extends JpaRepository<Admirer, Long> { }
