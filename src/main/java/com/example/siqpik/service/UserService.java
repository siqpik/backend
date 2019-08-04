package com.example.siqpik.service;

import com.example.siqpik.Admirer;
import com.example.siqpik.Admiring;
import com.example.siqpik.User;
import com.example.siqpik.repositories.AdmirerRepository;
import com.example.siqpik.repositories.AdmiringRepository;
import com.example.siqpik.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AdmiringRepository admiringRepo;
    private  final AdmirerRepository admirerRepo;
    {}
    public UserService(UserRepository userRepo, AdmiringRepository admiringRepo, AdmirerRepository admirerRepo) {
        this.userRepo = userRepo;
        this.admiringRepo = admiringRepo;
        this.admirerRepo = admirerRepo;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public Optional<User> getUser(Authentication authentication) {
        return authentication == null
                ? Optional.empty()
                : userRepo.findByUserName(authentication.getName());
    }

    public void createAdmirer(User userLogin, User otherUser) {
        admiringRepo.save(new Admiring(userLogin, otherUser));
        admirerRepo.save(new Admirer(userLogin, otherUser));
    }

    public Boolean isAdmiring(User user, User anotherUser) {
        return user.getAdmirings()
                .stream()
                .anyMatch(admiring -> admiring.getAdmiring()
                        .getId()
                        .equals(anotherUser.getId())
                );
    }

}
