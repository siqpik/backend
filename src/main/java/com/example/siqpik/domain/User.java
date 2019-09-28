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

    @Column(unique = true)
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
    private LocalDateTime date;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private Set<Admirer> admirers = new LinkedHashSet<>();  //Users I admire

    @OneToMany(mappedBy = "admirer")
    private Set<Admirer> admiring = new LinkedHashSet<>(); //Users admiring me

    @OneToMany(mappedBy = "user")
    private List<Tag> tags = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> picsUserLikes = new LinkedList<>();

    @OneToMany(mappedBy = "sender")
    private List<Request> requestsSend = new LinkedList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Request> requestsReceived = new LinkedList<>();


    /******************************************************
     *          Constructors
     *****************************************************/

    public User(){}

    public User(String userName, String name, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
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

    public Set<Admirer> getAdmiring() {
        return admiring;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Request> getRequestsReceived() {
        return requestsReceived;
    }

    public List<Request> getRequestsSend() {
        return requestsSend;
    }
}
