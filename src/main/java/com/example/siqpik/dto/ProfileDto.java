package com.example.siqpik.dto;
import com.example.siqpik.domain.Photo;
import com.example.siqpik.domain.User;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;
    private String profilePicUrl;

    public ProfileDto(User user) {
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .sorted(Comparator.comparing(Photo::getDate)
                        .reversed())
                .map(PhotoDto::new)
                .collect(toList());
        admirers = user.getAdmirers().size();
        admiring = user.getAdmiring().size();
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
