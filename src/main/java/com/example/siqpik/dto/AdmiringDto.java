package com.example.siqpik.dto;


import com.example.siqpik.Admiring;

public class AdmiringDto {


    private Long id;
    private Long userId;
    private String name;

    public AdmiringDto(Admiring admiring) {
        id = admiring.getId();
        userId = admiring.getAdmiring().getId();
        name = admiring.getAdmiring().getUserName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }
}
