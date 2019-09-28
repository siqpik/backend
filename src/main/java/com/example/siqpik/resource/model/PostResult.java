package com.example.siqpik.resource.model;

import com.example.siqpik.dto.PhotoDto;

public class PostResult {

    private final String profilePicUrl;
    private final String userName;
    private final PhotoDto photo;

    public PostResult(String profilePicUrl, String userName, PhotoDto photo) {
        this.profilePicUrl = profilePicUrl;
        this.userName = userName;
        this.photo = photo;
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