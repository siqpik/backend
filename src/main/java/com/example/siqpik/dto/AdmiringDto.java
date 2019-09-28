package com.example.siqpik.dto;


import com.example.siqpik.domain.Admirer;

public class AdmiringDto {


    private String name;

    public AdmiringDto(Admirer admirer) {
        name = admirer.getAdmired().getUserName();
    }

    public String getName() {
        return name;
    }
}
