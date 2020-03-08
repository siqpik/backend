package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Notification {

    public static final String REQUEST = "request";
    public static final String COMMENT = "comment";
    public static final String LIKE = "like";
    public static final String TAG = "tag";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user; //The user who will receive the notification

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Like like;

    @ManyToOne
    private AdmireRequest request;

    @ManyToOne
    private Tag tag;

    @Column
    private String type;

    @Column
    private Boolean viewed = false;

    @Column
    private LocalDateTime date = LocalDateTime.now(ZoneId.of("GMT"));

    public Notification(){ }

    public Notification(Comment comment) {
        this.user = comment.getPhoto().getUser();
        this.comment = comment;
        this.type = COMMENT;
    }

    public Notification(Like like) {
        this.user = like.getPic().getUser();
        this.like = like;
        this.type = LIKE;
    }

    public Notification(AdmireRequest request) {
        this.user = request.getReceiver();
        this.request = request;
        this.type = REQUEST;
    }

    public Notification(Tag tag) {
        this.user = tag.getUser();
        this.tag = tag;
        this.type = TAG;
    }

    public Long getId() {
        return id;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public AdmireRequest getRequest() {
        return request;
    }

    public Like getLike() {
        return like;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public String getSenderUserName() {
        switch (type) {
            case REQUEST:
                return request.getSender().getUserName();
            case LIKE:
                return like.getUser().getUserName();
            case COMMENT:
                return comment.getUser().getUserName();
            case TAG:
                return tag.getUser().getUserName();
            default:
                return "";
        }
    }

    public String getSenderProfilePic() {
        switch (type) {
            case REQUEST:
                return request.getSender().getProfilePicUrl();
            case LIKE:
                return like.getUser().getProfilePicUrl();
            case COMMENT:
                return comment.getUser().getProfilePicUrl();
            case TAG:
                return tag.getUser().getProfilePicUrl();
            default:
                return "";
        }
    }
}
