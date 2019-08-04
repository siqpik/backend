package com.example.siqpik.dto;

import com.example.siqpik.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileLoggedDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirersNumber;
    private Integer admiringNumber;


    public ProfileLoggedDto(User user){
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());
        admirersNumber = user.getAdmirers().size();
        admiringNumber = user.getAdmirings().size();
    }


    public String getName() {
        return name;
    }

    public Integer getAdmiringNumber() {
        return admiringNumber;
    }

    public Integer getAdmirersNumber() {
        return admirersNumber;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }
}
