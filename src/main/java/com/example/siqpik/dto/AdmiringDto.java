package com.example.siqpik.dto;


import com.example.siqpik.domain.Admirer;

public class AdmiringDto {


    private String name;

    public AdmiringDto(Admirer admirer) {
        name = admirer.getUser().getUserName();
    }

    public String getName() {
        return name;
    }
}
