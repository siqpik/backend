package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user;

    @Column
    private Comment comment;

    @Column
    private Like like;

    @Column
    private Request request;

    @Column
    private Tag tag;

    @Column
    private String type;

    @Column
    private Boolean viewed;

    @Column
    private LocalDateTime date;

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

    public Notification(Request request) {
        this.user = request.getStatus().equals("Pending")
                ? request.getReceiver()
                : request.getSender();
        this.request = request;
        this.type = "request";
    }

    public Notification(Tag tag) {
        this.user = tag.getUser();
        this.tag = tag;
        this.type = "tag";
    }
}
