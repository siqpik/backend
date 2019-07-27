package com.example.siqpik.dto;

import com.example.siqpik.Photo;

public class PhotoDto {

    private String url;

    public PhotoDto(Photo photo) {
        url = photo.getUrl();
    }

    public String getUrl() {
        return url;
    }
}
