package com.example.siqpik.dto;


import com.example.siqpik.Admiring;

public class AdmiringDto {


    private String name;

    public AdmiringDto(Admiring admiring) {
        name = admiring.getAdmiring().getUserName();
    }

    public String getName() {
        return name;
    }
}
