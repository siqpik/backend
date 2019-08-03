package com.example.siqpik;

import com.example.siqpik.dto.*;
import com.example.siqpik.service.PhotoService;
import com.example.siqpik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.util.stream.Collectors.toList;


@RestController
public class SiqpikController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/members")
    @ResponseBody
    String home() {
        return "Hello we are Ronn, Pancho, Laura and Yeray. AND WE ARE SIQPIK!!";
    }

    @PostMapping(value = "/picture/{id}")
    private ResponseEntity uploadPic(@RequestParam("file") MultipartFile file, @PathVariable Long id, Authentication auth) {
        return userService.getUser(auth)
                .filter(user -> user.getId().equals(id))
                .map(user -> {
                    try {
                        photoService.savePic(file, user);
                        return ResponseEntity.status(201).build();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(415).build();
                    }
                })
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping(value = "/loggedUser")
    private ResponseEntity getLoggedUser(Authentication auth){
        System.out.println(auth);
        return userService.getUser(auth)
                .map(user -> new ResponseEntity<>(new LoggedUserDto(user), HttpStatus.OK))
                .orElse(ResponseEntity.status(204).build());
    }

    @GetMapping(value = "/profile/{id}")
    private ResponseEntity getProfile(@PathVariable Long id, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> user.getId().equals(id)
                                ? new ResponseEntity<>(new ProfileLoggedDto(user), HttpStatus.OK)
                                : userService.getUserRepo()
                                .findById(id)
                                .map(user1 -> new ResponseEntity<>(
                                        new ProfileDto(user1)
                                        , HttpStatus.OK)
                                )
                                .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping(value = "/admirers/{id}")
    private ResponseEntity getAdmirers(@PathVariable Long id, Authentication aut) {
        return userService.getUser(aut)
                .map(user -> user.getId().equals(id)
                        ? new ResponseEntity<>(
                                user.getAdmirers()
                                .stream()
                                .map(AdmirerDto::new)
                                .collect(toList())
                                , HttpStatus.OK)
                        : ResponseEntity.status(403).build()
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping(value = "/admiring/{id}")
    private ResponseEntity getAdmirings(@PathVariable Long id, Authentication aut) {
        return userService.getUser(aut)
                .map(user -> user.getId().equals(id)
                        ? new ResponseEntity<>(
                        user.getAdmirings()
                                .stream()
                                .map(AdmiringDto::new)
                                .collect(toList())
                        , HttpStatus.OK)
                        : ResponseEntity.status(403).build()
                )
                .orElse(ResponseEntity.status(401).build());
    }


}
