package com.example.siqpik.dto;

import com.example.siqpik.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileLoggedDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;
    private String profilePicUrl;


    public ProfileLoggedDto(User user){
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());
        admirers = user.getAdmirers().size();
        admiring = user.getAdmirings().size();
        profilePicUrl = user.getProfilePicUrl();
    }


    public String getName() {
        return name;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }

    public Integer getAdmirers() {
        return admirers;
    }

    public Integer getAdmiring() {
        return admiring;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
