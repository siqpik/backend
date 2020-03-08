package com.example.siqpik.repositories;

import com.example.siqpik.domain.Photo;
import com.example.siqpik.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<List<Photo>> findAllByUserIsOrderByDate(User user);

    Optional<Photo> findById(Long id);
}
