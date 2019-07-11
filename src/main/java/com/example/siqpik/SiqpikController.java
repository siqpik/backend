package com.example.siqpik;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@RestController
public class SiqpikController {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siqpik",
            "api_key", "437532797296387",
            "api_secret", "WLHW9vutYNCgmK4hFvPAzja2Sb0"));

    @RequestMapping("/members")
    @ResponseBody
    String home() {
        return "Hello we are Ronn, Pancho, Laura and Yeray. AND WE ARE SIQPIK!!";
    }

    @PostMapping(value = "/image")
    public Map uploadIMG(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] pic = file.getBytes();
        Map photoInfo = cloudinary.uploader().upload(pic, ObjectUtils.emptyMap());
        Photo photo = new Photo(pic, photoInfo);
        return photoInfo;
    }
}
