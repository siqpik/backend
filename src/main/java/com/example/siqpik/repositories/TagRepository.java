package com.example.siqpik.repositories;

import com.example.siqpik.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TagRepository extends JpaRepository<Tag, Long> { }
