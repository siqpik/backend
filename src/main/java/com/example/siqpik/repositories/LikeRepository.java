package com.example.siqpik.repositories;

import com.example.siqpik.domain.Like;
import com.example.siqpik.domain.Photo;
import com.example.siqpik.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByPicAndUserIs(Photo pic, User user);

}
