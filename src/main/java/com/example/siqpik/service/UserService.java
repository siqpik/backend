package com.example.siqpik.service;

import com.example.siqpik.domain.*;
import com.example.siqpik.repositories.*;
import com.example.siqpik.resource.model.ProfileResult;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AdmirerRepository admirerRepo;
    private final AdmireRequestRepository requestRepo;
    private final NotificationRepository notificationRepo;

    public UserService(UserRepository userRepo,
                       AdmirerRepository admirerRepo,
                       AdmireRequestRepository requestRepo,
                       NotificationRepository notificationRepo) {
        this.userRepo = userRepo;
        this.admirerRepo = admirerRepo;
        this.requestRepo = requestRepo;
        this.notificationRepo = notificationRepo;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public AdmireRequestRepository getRequestRepo() {
        return requestRepo;
    }

    public NotificationRepository getNotificationRepo() {
        return notificationRepo;
    }

    public Optional<User> getUser(Authentication authentication) {
        return authentication == null
                ? Optional.empty()
                : userRepo.findByUserName(authentication.getName());
    }

    public void createAdmirer(User admirer, User user) {
        admirerRepo.save(new Admirer(admirer, user));
    }

    public List<ProfileResult> getProfileResults(String name){
         return userRepo.findUsersByUserNameContainingOrNameContaining(name, name)
                .stream()
                .map(user -> new ProfileResult(user.getUserName(), user.getName()))
                 .collect(toList());
    }

    public void createRequestToAdmire(User sender, User receive) {
        AdmireRequest request = new AdmireRequest(sender, receive);
        Notification notification = new Notification(request);
        requestRepo.save(request);
        notificationRepo.save(notification);
    }

    public Boolean updateRequestAndCreateAdmire(AdmireRequest admireRequest, Boolean result) {
        admireRequest.setResponseDate(LocalDateTime.now(ZoneId.of("GMT")));
        admireRequest.setStatus(result
                ? AdmireRequest.ACCEPTED
                : AdmireRequest.CANCELED
        );
        if (result) createAdmirer(admireRequest.getSender(), admireRequest.getReceiver());
        requestRepo.save(admireRequest);
        return result;
    }

    public Optional<AttemptedPics> getLastAttempt(User user) {
        return user.getAttemptedPics()
                .stream()
                .filter(attemptedPic -> attemptedPic
                        .getDate()
                        .toLocalDate()
                        .isEqual(LocalDate.now(ZoneId.of("GMT"))))
                .findFirst();
    }

    public Boolean limitOfAttemptsReached(User user) {
        return getLastAttempt(user)
                .map(attemptedPics -> attemptedPics.getAttempts() > 2)
                .orElse(false);
    }

}
