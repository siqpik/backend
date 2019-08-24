package com.example.siqpik.resource.model;

public class ProfileResult {

    private final String userName;
    private final String name;
    private final String avatarUrl;

    public ProfileResult(String userName, String name) {
        this.userName = userName;
        this.name = name;
        this.avatarUrl = "https://s2.eestatic.com/2018/09/05/deportes/baloncesto/Kobe_Bryant-Michael_O-Neill-NBA-Baloncesto_335727461_95913355_1024x576.jpg";
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
