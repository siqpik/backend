package com.example.siqpik.resource;

import com.example.siqpik.domain.Photo;
import com.example.siqpik.resource.model.ProfileResult;
import com.example.siqpik.dto.ProfileDto;
import com.example.siqpik.dto.ProfileLoggedDto;
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

    public ProfileResource(UserService userService) {
        this.userService = userService;
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
                .map(loggedUser -> userService.getUserRepo()
                        .findByUserName(userName)
                        .map(user -> new ResponseEntity<>(new ProfileDto(loggedUser, user), HttpStatus.OK))
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/changeProfilePic/{picUrl}")
    private ResponseEntity changeProfilePic(@PathVariable String picUrl, Authentication auth){
        return userService.getUser(auth)
                .map(user -> {
                    if(user.getPhotos()
                            .stream()
                            .map(Photo::getUrl)
                            .anyMatch(url -> url.equals(picUrl))
                    ) {
                        user.setProfilePicUrl(picUrl);
                        return ResponseEntity.status(200).build();
                    } else {
                        return ResponseEntity.status(404).build();
                    }
                })
                .orElse(ResponseEntity.status(401).build());
    }

}
