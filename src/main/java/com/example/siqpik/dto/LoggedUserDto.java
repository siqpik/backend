package com.example.siqpik.dto;

import com.example.siqpik.domain.User;

public class LoggedUserDto {

    private String name;

    public LoggedUserDto(User user){
        name = user.getUserName();
    }

    public String getName() {
        return name;
    }
}
