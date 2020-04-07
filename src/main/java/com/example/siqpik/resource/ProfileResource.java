package com.example.siqpik.resource;

import com.example.siqpik.dto.ProfileDto;
import com.example.siqpik.resource.model.ProfileResult;
import com.example.siqpik.service.PhotoService;
import com.example.siqpik.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileResource {

    private final UserService userService;
    private final PhotoService photoService;

    public ProfileResource(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProfileResult>> getProfileResults(@PathVariable("name") String name){
        return new ResponseEntity<>(
                userService.getProfileResults(name),
                HttpStatus.OK
        );
    }

    @GetMapping("/{userName}")
    private ResponseEntity getProfile(@PathVariable String userName, Authentication auth) {
        return userService.getUser(auth)
                .map(loggedUser -> userService.findByUserName(userName)
                        .map(user -> new ResponseEntity<>(new ProfileDto(loggedUser, user), HttpStatus.OK))
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/changeProfilePic/{picId}")
    private ResponseEntity changeProfilePic(@PathVariable Long picId, Authentication auth){
        return userService.getUser(auth)
                .map(user -> photoService.getPhotoRepo().findById(picId)
                        .map(pic -> {
                            user.setProfilePicUrl(pic.getUrl());
                            return ResponseEntity.status(200).build();
                        })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

}
