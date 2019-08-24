package com.example.siqpik.controller.model;

public class ProfileResult {

    private final String userName;
    private final String name;

    public ProfileResult(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }
}
