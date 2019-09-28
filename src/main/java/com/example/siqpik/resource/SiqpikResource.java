package com.example.siqpik.resource;

import com.example.siqpik.domain.User;
import com.example.siqpik.dto.*;
import com.example.siqpik.repositories.UserRepository;
import com.example.siqpik.service.PhotoService;
import com.example.siqpik.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class SiqpikResource {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @PostMapping("/picture")
    public ResponseEntity uploadPic(@RequestParam("file") MultipartFile file, Authentication auth) {
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

    @PostMapping("/admire/{userName}")
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
//        return userService.getUser(auth)
//                .map(user -> photoService.getPhotoRepo()
//                        .findById(id)
//                        .filter(pic -> userService.isAdmiring(user, pic.getUser()))
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


    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        map.put(key, value);
        return map;
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {

        System.out.println("entered");
        System.out.println(user.getUserName());

        System.out.println(user.getPassword());

        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(makeMap("error", "Fields Empty"), HttpStatus.FORBIDDEN);
        }

        Optional<User> newUser = userService.getUserRepo().findByUserName(user.getUserName());

        if (newUser.isPresent()) {
            return new ResponseEntity<>(makeMap("error", "Username already exists"), HttpStatus.CONFLICT);
        }

        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));


        userService.getUserRepo().save(user);

        return new ResponseEntity<>(makeMap("id", user.getId()), HttpStatus.CREATED);

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
                        user.getAdmiring()
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
                        .map(request -> {
                            if (result && user.getRequestsReceived().contains(request)) {
                                userService.createAdmirer(request.getSender(), user);
                                request.setResponseDate(LocalDateTime.now(ZoneId.of("GMT")));
                                request.setStatus("Accepted");
                                return ResponseEntity.status(201).build();
                            } else {
                                request.setResponseDate(LocalDateTime.now(ZoneId.of("GMT")));
                                request.setStatus("Canceled");
                                return ResponseEntity.status(200).build();
                            }
                        })
                        .orElse(ResponseEntity.status(404).build())
                )
                .orElse(ResponseEntity.status(401).build());
    }
}
