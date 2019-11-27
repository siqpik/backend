package com.example.siqpik.resource;


import com.example.siqpik.dto.AttemptedPicsDto;
import com.example.siqpik.service.PhotoService;
import com.example.siqpik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api)")
public class CameraResource {

    private final UserService userService;
    private final PhotoService photoService;

    public CameraResource(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @GetMapping("/attempts")
    public ResponseEntity getAttemptedPics(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> userService.getLastAttempt(user)
                        .map(attemptedPic -> new ResponseEntity<>(new AttemptedPicsDto(attemptedPic), HttpStatus.OK))
                        .orElse(new ResponseEntity<>(new AttemptedPicsDto(), HttpStatus.OK))
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/attempts")
    public ResponseEntity addAttempt(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> userService.getLastAttempt(user)
                        .map(attemptedPic -> {
                            photoService.addAttempt(attemptedPic);
                            return ResponseEntity.status(200).build();
                        })
                        .orElseGet(() -> {
                            photoService.createAttemptPics(user);
                            return ResponseEntity.status(200).build();
                        })
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/picture")
    public ResponseEntity uploadPic(@RequestParam("file") MultipartFile file, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> {
                    if (userService.limitOfAttemptsReached(user)) {
                        return ResponseEntity.status(409).build();
                    } else {
                        try {
                            photoService.savePic(file, user);
                            return ResponseEntity.status(201).build();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return ResponseEntity.status(415).build();
                        }
                    }
                })
                .orElse(ResponseEntity.status(401).build());
    }
}

