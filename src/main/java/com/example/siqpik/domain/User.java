package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String profilePicUrl = "https://static.hiphopdx.com/2017/11/B-Real-827x620.jpg";

    @Column
    private LocalDateTime date = LocalDateTime.now(ZoneId.of("GMT"));

    @OneToMany(mappedBy = "user")
    @OrderBy("date DESC")
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(mappedBy = "admired")
    private Set<Admirer> admirers = new LinkedHashSet<>();  //Users I admire

    @OneToMany(mappedBy = "admirer")
    private Set<Admirer> admirings = new LinkedHashSet<>(); //Users admiring me

    @OneToMany(mappedBy = "user")
    private List<Tag> tags = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> picsUserLikes = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new LinkedList<>();

    @OneToMany(mappedBy = "sender")
    private List<AdmireRequest> requestsSend = new LinkedList<>();

    @OneToMany(mappedBy = "receiver")
    private List<AdmireRequest> requestsReceived = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<AttemptedPics> attemptedPics = new LinkedList<>();


    /******************************************************
     *          Constructors
     *****************************************************/

    public User(){}

    public User(String userName, String name, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public String getUserName() {
        return userName;
    }

    public Long getId() {
        return id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getPassword() {
        return password;
    }

    public Set<Admirer> getAdmirers() {
        return admirers;
    }

    public Set<Admirer> getAdmirings() {
        return admirings;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<AdmireRequest> getRequestsReceived() {
        return requestsReceived;
    }

    public List<AdmireRequest> getRequestsSend() {
        return requestsSend;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<AttemptedPics> getAttemptedPics() {
        return attemptedPics;
    }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
