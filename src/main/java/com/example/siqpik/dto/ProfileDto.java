package com.example.siqpik.dto;
import com.example.siqpik.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;
    private String profilePic;

    public ProfileDto(User user) {
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());
        admirers = user.getAdmirers().size();
        admiring = user.getAdmirings().size();
        profilePic = null;
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

    public String getProfilePic() {
        return profilePic;
    }
}
