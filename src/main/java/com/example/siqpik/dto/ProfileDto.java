package com.example.siqpik.dto;
import com.example.siqpik.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;

    public ProfileDto(User user){ ;
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());

    }

    public String getName() {
        return name;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }
}
