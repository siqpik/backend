package com.example.siqpik.dto;

import com.example.siqpik.User;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserDto {

    private Long id;
    private String name;
    private List<PhotoDto> pics;

    public UserDto(User user){
        id = user.getId();
        name = user.getName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new).collect(toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }
}
