package com.example.siqpik.service;

import com.example.siqpik.User;
import com.example.siqpik.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUser(Authentication authentication) {
        return authentication == null
                ? Optional.empty()
                : userRepo.findByUserName(authentication.getName());
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }
}
