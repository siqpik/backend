package com.example.siqpik;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.siqpik.dto.UserDto;
import com.example.siqpik.repositories.PhotoRepository;
import com.example.siqpik.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@RestController
public class SiqpikController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PhotoRepository photoRepo;

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siqpik",
            "api_key", "437532797296387",
            "api_secret", "WLHW9vutYNCgmK4hFvPAzja2Sb0"));

    @RequestMapping("/members")
    @ResponseBody
    String home() {
        return "Hello we are Ronn, Pancho, Laura and Yeray. AND WE ARE SIQPIK!!";
    }

    @PostMapping(value = "/picture/{id}")
    public ResponseEntity uploadIMG(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws IOException {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            byte[] pic = file.getBytes();
            Map photoInfo = cloudinary.uploader().upload(pic, ObjectUtils.emptyMap());
            Photo photo = new Photo(user.get(), pic, photoInfo);
            photoRepo.save(photo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(value = "/user/{id}")
    public UserDto userInfo(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(UserDto::new)
                .orElse(null);
    }


}
