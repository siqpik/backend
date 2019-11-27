package com.example.siqpik.dto;

import com.example.siqpik.domain.AttemptedPics;

public class AttemptedPicsDto {

    private Short attempts;

    public AttemptedPicsDto(AttemptedPics attemptedPics) {

        this.attempts = attemptedPics.getAttempts();
    }

    public AttemptedPicsDto() {
        this.attempts = 0;
    }

    public Short getAttempts() {
        return attempts;
    }
}
