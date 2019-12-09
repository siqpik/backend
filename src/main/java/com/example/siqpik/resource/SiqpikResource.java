package com.example.siqpik.resource;

import com.example.siqpik.domain.Notification;
import com.example.siqpik.dto.AdmirerDto;
import com.example.siqpik.dto.AdmiringDto;
import com.example.siqpik.dto.NotificationDto;
import com.example.siqpik.service.PhotoService;
import com.example.siqpik.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class SiqpikResource {

    private final UserService userService;
    private final PhotoService photoService;

    public SiqpikResource(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @PostMapping("/picture/{id}")
    private ResponseEntity likePic(@PathVariable Long id, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> photoService.getPhotoRepo()
                        .findById(id)
                        .map(pic -> {
                            photoService.createLike(user, pic);
                            return ResponseEntity.status(201).build();
                        })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

//    @PostMapping("/comment/{id]")
//    private ResponseEntity putComment(@PathVariable Long id, @RequestBody String commentary, Authentication auth) {
//        return userService.getAdmired(auth)
//                .map(user -> photoService.getPhotoRepo()
//                        .findById(id)
//                        .filter(pic -> userService.isAdmiring(user, pic.getAdmired()))
//                        .map(pic -> {
//                            photoService.createComment(user, pic, commentary);
//                            return ResponseEntity.status(201).build();
//                        })
//                        .orElse(ResponseEntity.status(404).build())
//
//                ).orElse(ResponseEntity.status(401).build());
//    }

    @GetMapping("/userLogin")
    private ResponseEntity isUserLogin(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> ResponseEntity.status(200).build())
                .orElse(ResponseEntity.status(404).build());
    }

    @GetMapping("/admirers")
    private ResponseEntity getAdmirers(Authentication aut) {
        return userService.getUser(aut)
                .map(user ->  new ResponseEntity<>(
                        user.getAdmirers()
                                .stream()
                                .map(AdmirerDto::new)
                                .collect(toList())
                        , HttpStatus.OK
                        )
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/admiring")
    private ResponseEntity getAdmirings(Authentication aut) {
        return userService.getUser(aut)
                .map(user ->  new ResponseEntity<>(
                        user.getAdmirings()
                                .stream()
                                .map(AdmiringDto::new)
                                .collect(toList())
                        , HttpStatus.OK)
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/request/{userName}")
    private ResponseEntity requestAdmire(@PathVariable String userName, Authentication auth) {
        return userService.getUser(auth)
                .map(sender -> userService.getUserRepo()
                        .findByUserName(userName)
                        .map(receiver -> {
                            userService.createRequestToAdmire(sender, receiver);
                            return ResponseEntity.status(201).build();
                        })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/request/{requestId}/{result}")
    private ResponseEntity resultRequestAdmire(@PathVariable Long requestId, @PathVariable Boolean result, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> userService.getRequestRepo()
                        .findById(requestId)
                        .map(admireRequest -> {
                            if (result && user.getRequestsReceived().contains(admireRequest)) {
                                userService.createAdmirer(admireRequest.getSender(), user);
                                admireRequest.setResponseDate(LocalDateTime.now(ZoneId.of("GMT")));
                                admireRequest.setStatus("Accepted");
                                return ResponseEntity.status(201).build();
                            } else if (!user.getRequestsReceived().contains(admireRequest)) {
                                return ResponseEntity.status(404).build();
                            } else {
                                admireRequest.setResponseDate(LocalDateTime.now(ZoneId.of("GMT")));
                                admireRequest.setStatus("Canceled");
                                return ResponseEntity.status(200).build();
                            }
                        })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/newNotifications")
    private ResponseEntity getNumberOfNewNotifications(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> new ResponseEntity<>(
                        user.getNotifications()
                                .stream()
                                .filter(notification -> !notification.getViewed())
                                .count()
                        ,HttpStatus.OK
                        )
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/notifications")
    private ResponseEntity getNotifications(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> new ResponseEntity<> (
                        user.getNotifications()
                                .stream()
                                .map(NotificationDto::new)
                                .collect(toList())
                        , HttpStatus.OK
                        )
                )
                .orElse(ResponseEntity.status(401).build());
    }
}
