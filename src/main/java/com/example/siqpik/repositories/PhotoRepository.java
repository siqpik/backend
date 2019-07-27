package com.example.siqpik.repositories;

import com.example.siqpik.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PhotoRepository extends JpaRepository<Photo, Long> { }
