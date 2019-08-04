package com.example.siqpik.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.siqpik.Comment;
import com.example.siqpik.Like;
import com.example.siqpik.Photo;
import com.example.siqpik.User;
import com.example.siqpik.repositories.CommentRepository;
import com.example.siqpik.repositories.LikeRepository;
import com.example.siqpik.repositories.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class PhotoService {

    private final PhotoRepository photoRepo;
    private final LikeRepository likeRepo;
    private final CommentRepository commentRepo;

    public PhotoService(PhotoRepository photoRepo, LikeRepository likeRepo, CommentRepository commentRepo) {
        this.photoRepo = photoRepo;
        this.likeRepo = likeRepo;
        this.commentRepo = commentRepo;
    }

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siqpik",
            "api_key", "437532797296387",
            "api_secret", "WLHW9vutYNCgmK4hFvPAzja2Sb0"));

    public PhotoRepository getPhotoRepo() {
        return photoRepo;
    }

    public void savePic(MultipartFile file, User user) throws IOException {
        byte[] pic = file.getBytes();
        Map photoInfo = cloudinary.uploader().upload(pic, ObjectUtils.emptyMap());
        photoRepo.save(new Photo(user, pic, photoInfo));
    }

    public void createLike(User user, Photo pic) {
        likeRepo.save(new Like(user, pic));
    }

    public void createComment(User user, Photo pic, String commentary) {
        commentRepo.save(new Comment(user, pic, commentary));
    }
}
