package com.example.siqpik.resource;

import com.example.siqpik.domain.User;
import com.example.siqpik.service.UserService;
import com.example.siqpik.util.GenericHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicAuthenticationResource {

    private final UserService userService;

  public BasicAuthenticationResource(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> createUser(@RequestBody User user) {
    if (!requestIsValid(user)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    if (userService.existsByEmail(user.getEmail())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
    }

    if (userService.existsByUserName(user.getUserName())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
    userService.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  private Boolean requestIsValid(User user){
    return GenericHelper.areNotNullNorEmpty(
        user.getUserName(),
        user.getPassword(),
        user.getEmail()
    );
  }
}
