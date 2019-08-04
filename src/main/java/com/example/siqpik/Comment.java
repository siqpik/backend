package com.example.siqpik;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Photo pic;

    private String commentary;

    public Comment(User user, Photo pic, String commentary) {
        this.user = user;
        this.pic = pic;
        this.commentary = commentary;
    }

    public User getUser() {
        return user;
    }

    public String getCommentary() {
        return commentary;
    }
}
