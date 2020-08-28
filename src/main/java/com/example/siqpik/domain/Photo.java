package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "photos")
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
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

    @Column(name = "date")
    private LocalDateTime date;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Photo() {
    }

    public Photo(User user, Map cloudinaryInfo) {
        this.user = user;
        this.url = (String) cloudinaryInfo.get("url");
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setUrl(String url){
        this.url = url;
    }

}
