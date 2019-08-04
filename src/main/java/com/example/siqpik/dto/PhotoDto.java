package com.example.siqpik.dto;

import com.example.siqpik.Photo;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class PhotoDto {

    private Long id;
    private String url;
    private List<String> likes;
    private List<String> tags;
    private List<CommentDto> comments;

    public PhotoDto(Photo photo) {
        id = photo.getId();
        url = photo.getUrl();
        likes = photo.getLikes()
                .stream()
                .map(like -> like.getUser().getUserName())
                .collect(toList());
        tags = photo.getTags()
                .stream()
                .map(tag -> tag.getUser().getUserName())
                .collect(toList());
        comments = photo.getComments()
                .stream()
                .map(CommentDto::new)
                .collect(toList());
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getLikes() {
        return likes;
    }

    public List<String> getUsersTag() {
        return tags;
    }
}
