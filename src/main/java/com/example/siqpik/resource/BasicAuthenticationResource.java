package com.example.siqpik.resource;

import com.example.siqpik.domain.User;
import com.example.siqpik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthenticationResource {

    @Autowired
    private UserService userService;

    /*@GetMapping("/basicauth")
    public AuthenticationBean authenticate() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new AuthenticationBean("You are authenticated");
    }*/
    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody User user) {
        return user.getUserName().isEmpty() || user.getPassword().isEmpty()
                ? ResponseEntity.status(403).build()
                : userService.getUserRepo().findByUserName(user.getUserName())
                .map(user1 -> ResponseEntity.status(409).build())
                .orElseGet(() -> {
                    user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
                    userService.getUserRepo().save(user);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }
}
