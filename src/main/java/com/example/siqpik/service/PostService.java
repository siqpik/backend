package com.example.siqpik.service;

import com.example.siqpik.domain.Photo;
import com.example.siqpik.domain.User;
import com.example.siqpik.dto.PhotoDto;
import com.example.siqpik.resource.model.PostResult;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    private final UserService userService;


    public PostService(UserService userService) {
        this.userService = userService;
    }

    private static int compare(PostResult post1, PostResult post2) {
        return post1.getPhoto().getDate()
                .compareTo(post2.getPhoto().getDate());
    }

    public List<PostResult> getPosts(Authentication auth){
        return userService.getUser(auth)
                .map(user -> {
                    List<PostResult> admiredsPosts = user.getAdmirings()
                            .stream()
                            .flatMap(admiring -> admiring.getAdmired().getPhotos().stream()
                                    .map(pic ->
                                            new PostResult(
                                                    admiring.getAdmired().getProfilePicUrl(),
                                                    admiring.getAdmired().getUserName(),
                                                    new PhotoDto(pic),
                                                    logUserLikePic(pic, user)

                                            )
                                    )
                            ).collect(toList());

                    List<PostResult> myPosts = user.getPhotos().stream()
                            .map(pic -> new PostResult(
                                    user.getProfilePicUrl(),
                                    user.getUserName(),
                                    new PhotoDto(pic),
                                    logUserLikePic(pic, user)
                            )).collect(toList());

                    myPosts.addAll(admiredsPosts);

                    myPosts.sort(Comparator.comparing(post -> post.getPhoto().getDate()));

                    return myPosts;

                }).orElse(Collections.emptyList());
    }

    public Boolean logUserLikePic(Photo photo, User user) {
        return photo.getLikes()
                .stream()
                .anyMatch(like -> like.getUser().getId().equals(user.getId()));

    }
}
