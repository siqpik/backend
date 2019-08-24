package com.example.siqpik.dto;
import com.example.siqpik.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;

    public ProfileDto(User user){ ;
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());
        admirers = user.getAdmirers().size();
        admiring = user.getAdmirings().size();

    }

    public String getName() {
        return name;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }
}
