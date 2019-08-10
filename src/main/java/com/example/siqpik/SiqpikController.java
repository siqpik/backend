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
import java.util.List;

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

    @PostMapping(value = "/picture")
    private ResponseEntity uploadPic(@RequestParam("file") MultipartFile file, Authentication auth) {
        return userService.getUser(auth)
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

    @PostMapping(value = "/admire/{userName}")
    private ResponseEntity admireUser(@PathVariable String userName, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> userService.getUserRepo()
                            .findByUserName(userName)
                            .map(otherUser -> {
                                userService.createAdmirer(user, otherUser);
                                return ResponseEntity.status(201).build();
                            })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping(value = "/picture/{id}")
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

    @PostMapping(value = "/comment/{id]")
    private ResponseEntity putComment(@PathVariable Long id, @RequestBody String commentary, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> photoService.getPhotoRepo()
                        .findById(id)
                        .filter(pic -> userService.isAdmiring(user, pic.getUser()))
                        .map(pic -> {
                            photoService.createComment(user, pic, commentary);
                            return ResponseEntity.status(201).build();
                        })
                        .orElse(ResponseEntity.status(404).build())

                ).orElse(ResponseEntity.status(401).build());
    }

//    @PostMapping(value = "/tag/{id}")
//    private ResponseEntity tagUsersOnPic(@PathVariable Long id, @RequestBody List<String> userNames, Authentication auth) {
//        userNames.stream()
//                .filter(names ->)
//    }



    @GetMapping(value = "/userLogin")
    private ResponseEntity isUserLogin(Authentication auth) {
        return userService.getUser(auth)
                .map(user -> ResponseEntity.status(200).build())
                .orElse(ResponseEntity.status(404).build());
    }

    @GetMapping(value = "/profile/{userName}")
    private ResponseEntity getProfile(@PathVariable String userName, Authentication auth) {
        return userService.getUser(auth)
                .map(user -> user.getUserName().equals(userName)
                        ? new ResponseEntity<>(new ProfileLoggedDto(user), HttpStatus.OK)
                        : userService.getUserRepo().findByUserName(userName)
                        .map(user1 -> new ResponseEntity<>(
                                new ProfileDto(user1),
                                HttpStatus.OK)
                        )
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping(value = "/admirers")
    private ResponseEntity getAdmirers(Authentication aut) {
        return userService.getUser(aut)
                .map(user ->  new ResponseEntity<>(
                                user.getAdmirers()
                                .stream()
                                .map(AdmirerDto::new)
                                .collect(toList())
                                , HttpStatus.OK)
                )
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping(value = "/admiring")
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
}
