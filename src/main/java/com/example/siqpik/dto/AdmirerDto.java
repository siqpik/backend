package com.example.siqpik.dto;

import com.example.siqpik.Admirer;

public class AdmirerDto {

    private String name;

    public AdmirerDto(Admirer admirer) {
        name = admirer.getAdmirer().getUserName();
    }

    public String getName() {
        return name;
    }

}
