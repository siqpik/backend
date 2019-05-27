package com.example.siqpik;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;


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

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Map<String, Object> signUpPlayer(@RequestParam("file") MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    }
}
