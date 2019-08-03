package com.example.siqpik.dto;

import com.example.siqpik.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileLoggedDto {

    private Long id;
    private String name;
    private List<PhotoDto> pics;
    private Integer admirersNumber;
    private Integer admiringsNumber;


    public ProfileLoggedDto(User user){
        id = user.getId();
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .map(PhotoDto::new)
                .collect(toList());
        admirersNumber = user.getAdmirers().size();
        admiringsNumber = user.getAdmirings().size();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAdmiringsNumber() {
        return admiringsNumber;
    }

    public Integer getAdmirersNumber() {
        return admirersNumber;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }
}
