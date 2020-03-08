package com.example.siqpik.dto;

import com.example.siqpik.domain.Notification;

public class NotificationDto {

    private Long id;
    private String userProfilePic;
    private String senderUserName;
    private Boolean viewed;
    private String type;
    private String status;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.userProfilePic = notification.getSenderProfilePic();
        this.senderUserName = notification.getSenderUserName();
        this.viewed = notification.getViewed();
        this.type = notification.getType();
        this.status = notification.getType().equals(Notification.REQUEST)
                ? notification.getRequest().getStatus()
                : null;
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

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public String getSenderUserName() {
        return senderUserName;
    }
}
