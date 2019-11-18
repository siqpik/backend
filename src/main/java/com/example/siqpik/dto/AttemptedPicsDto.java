package com.example.siqpik.dto;

import com.example.siqpik.domain.AttemptedPics;

public class AttemptedPicsDto {

    private Short attemps;

    public AttemptedPicsDto(AttemptedPics attemptedPics) {

        this.attemps = attemptedPics.getAttempts();
    }

    public AttemptedPicsDto() {
        this.attemps = 0;
    }
}
