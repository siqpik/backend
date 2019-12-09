package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user;

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

    public Notification(){

    }

    public Notification(Comment comment) {
        this.user = comment.getPhoto().getUser();
        this.comment = comment;
        this.type = "comment";
    }

    public Notification(Like like) {
        this.user = like.getUser();
        this.like = like;
        this.type = "like";
    }

    public Notification(AdmireRequest request) {
        this.user = request.getSender();
        this.request = request;
        this.type = "request";
    }

    public Notification(Tag tag) {
        this.user = tag.getUser();
        this.tag = tag;
        this.type = "tag";
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

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
