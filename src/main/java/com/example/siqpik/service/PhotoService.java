package com.example.siqpik.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.siqpik.Photo;
import com.example.siqpik.User;
import com.example.siqpik.repositories.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class PhotoService {

    private final PhotoRepository photoRepo;

    public PhotoService(PhotoRepository photoRepo) {
        this.photoRepo = photoRepo;
    }

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siqpik",
            "api_key", "437532797296387",
            "api_secret", "WLHW9vutYNCgmK4hFvPAzja2Sb0"));

    public void savePic(MultipartFile file, User user) throws IOException {
        byte[] pic = file.getBytes();
        Map photoInfo = cloudinary.uploader().upload(pic, ObjectUtils.emptyMap());
        Photo photo = new Photo(user, pic, photoInfo);
        photoRepo.save(photo);
    }
}
