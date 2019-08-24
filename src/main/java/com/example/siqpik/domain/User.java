package com.example.siqpik;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String userName;

    private String email;
    private String password;
    private String name;
    private String profilePic;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private Set<Admirer> admirers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Admiring> admirings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Tag> tags = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> picsUserLikes = new LinkedList<>();



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

    public Set<Admiring> getAdmirings() {
        return admirings;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }
}
