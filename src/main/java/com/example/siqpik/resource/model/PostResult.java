package com.example.siqpik.resource.model;

import com.example.siqpik.dto.PhotoDto;

public class PostResult {

    private final String profilePicUrl;
    private final String userName;
    private final PhotoDto photo;
    private final Boolean iLikeThisPic;

    public PostResult(String profilePicUrl, String userName, PhotoDto photo, Boolean iLikeThisPic) {
        this.profilePicUrl = profilePicUrl;
        this.userName = userName;
        this.photo = photo;
        this.iLikeThisPic = iLikeThisPic;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUserName() {
        return userName;
    }

    public PhotoDto getPhoto() {
        return photo;
    }
}