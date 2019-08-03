package com.example.siqpik.dto;

import com.example.siqpik.User;

public class LoggedUserDto {

    private Long id;
    private String name;

    public LoggedUserDto(User user){
        id = user.getId();
        name = user.getUserName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
