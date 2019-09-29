package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Photo pic;

    private String commentary;

    public Comment() {}

    public Comment(User user, Photo photo, String commentary) {
        this.user = user;
        this.pic = photo;
        this.commentary = commentary;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
    }

    public User getUser() {
        return user;
    }

    public String getCommentary() {
        return commentary;
    }

    public Photo getPhoto() {
        return pic;
    }
}
