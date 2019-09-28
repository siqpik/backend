package com.example.siqpik.resource;

import com.example.siqpik.resource.model.ProfileResult;
import com.example.siqpik.dto.ProfileDto;
import com.example.siqpik.dto.ProfileLoggedDto;
import com.example.siqpik.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
