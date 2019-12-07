package com.example.siqpik.repositories;

import com.example.siqpik.domain.AdmireRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdmireRequestRepository extends JpaRepository<AdmireRequest, Long> {
}
