package com.example.siqpik;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "photos")
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pic")
    private List<Tag> tags = new LinkedList<>();

    @OneToMany(mappedBy = "pic")
    private List<Like> likes = new LinkedList<>();

    @OneToMany(mappedBy = "pic")
    private List<Comment> comments = new LinkedList<>();

    @Column
    private String url;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Photo(){}

    public Photo(User user, Map cloudinaryInfo) {
        this.user = user;
        this.url = (String) cloudinaryInfo.get("url");
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
