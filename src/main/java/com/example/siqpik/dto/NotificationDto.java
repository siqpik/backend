package com.example.siqpik.dto;

import com.example.siqpik.domain.Notification;

public class NotificationDto {

    private Long id;
    private String userName;
    private String userProfilePic;
    private Boolean viewed;
    private String type;
    private String status;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.userName = notification.getUser().getUserName();
        this.userProfilePic = notification.getUser().getProfilePicUrl();
        this.viewed = notification.getViewed();
        this.type = notification.getType();
        if (notification.getType().equals("request")) {
            this.status = notification.getRequest().getStatus();
        }
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }
}
