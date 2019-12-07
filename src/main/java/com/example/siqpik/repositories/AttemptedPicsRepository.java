package com.example.siqpik.repositories;

import com.example.siqpik.domain.AttemptedPics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AttemptedPicsRepository extends JpaRepository<AttemptedPics, Long> {
    AttemptedPics findTopByOrderByIdDesc();
}
