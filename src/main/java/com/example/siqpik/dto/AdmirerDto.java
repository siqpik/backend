package com.example.siqpik.dto;

import com.example.siqpik.Admirer;

public class AdmirerDto {

    private Long id;
    private Long userId;
    private String name;

    public AdmirerDto(Admirer admirer) {
        id = admirer.getId();
        userId = admirer.getAdmirer().getId();
        name = admirer.getAdmirer().getName();
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
