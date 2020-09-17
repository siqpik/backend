package com.example.siqpik.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.siqpik.domain.*;
import com.example.siqpik.repositories.AttemptedPicsRepository;
import com.example.siqpik.repositories.CommentRepository;
import com.example.siqpik.repositories.LikeRepository;
import com.example.siqpik.repositories.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.springframework.data.util.Optionals.ifPresentOrElse;

@Service
public class PhotoService {

    private final String CLOUD_NAME = "siqpik";
    private final String API_KEY = "437532797296387";
    private final String API_SECRET = "WLHW9vutYNCgmK4hFvPAzja2Sb0";
    private final String DELETE_PIC_URL = "https://res.cloudinary.com/siqpik/image/upload/v1598618292/deletePic.jpg`";

    private final PhotoRepository photoRepo;
    private final LikeRepository likeRepo;
    private final CommentRepository commentRepo;
    private final AttemptedPicsRepository attemptedPicsRepo;

    public PhotoService(PhotoRepository photoRepo, LikeRepository likeRepo, CommentRepository commentRepo, AttemptedPicsRepository attemptedPicsRepo) {
        this.photoRepo = photoRepo;
        this.likeRepo = likeRepo;
        this.commentRepo = commentRepo;
        this.attemptedPicsRepo = attemptedPicsRepo;
    }

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", CLOUD_NAME,
            "api_key", API_KEY,
            "api_secret", API_SECRET));

    public PhotoRepository getPhotoRepo() {
        return photoRepo;
    }

    public void savePic(MultipartFile file, User user) throws IOException {
        Map photoInfo = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        photoRepo.save(new Photo(user, photoInfo));
    }

    public void createOrDeleteLike(User user, Photo pic) {
        ifPresentOrElse(
                likeRepo.findByPicAndUserIs(pic, user),
                like -> likeRepo.deleteById(like.getId()),
                () -> likeRepo.save(new Like(user, pic))
        );
    }

    public void createComment(User user, Photo pic, String commentary) {
        commentRepo.save(new Comment(user, pic, commentary));
    }

    public void addAttempt(AttemptedPics attemptedPics) {
            attemptedPics.addAttempt();
            attemptedPicsRepo.save(attemptedPics);
    }

    public void createAttemptPics(User user) {
        attemptedPicsRepo.save(new AttemptedPics(user));
    }

    public void deletePic(Photo pic){
        pic.setUrl(DELETE_PIC_URL);
        photoRepo.save(pic);
    }

}
